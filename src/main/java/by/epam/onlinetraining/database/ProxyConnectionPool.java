package by.epam.onlinetraining.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ProxyConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String RESOURCE_FILE_NAME = "db.properties";
    private static final String POOL_CAPACITY_PROPERTY = "db.poolCapacity";
    private static final String EMPTY_POOL = "Connection pool is empty.";
    private static final String CANT_ENABLE_AUTO_COMMIT = "Can't enable auto commit.";
    private static final String FILE_NOT_FOUND = "File not found.";
    private static final ReentrantLock initializationLock = new ReentrantLock();
    private static final ReentrantLock connectionLock = new ReentrantLock();
    private static final ReentrantLock reconnectionLock = new ReentrantLock();
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static ProxyConnectionPool instance;
    private Deque<ProxyConnection> connections;
    private Semaphore semaphore;
    private ConnectionCreator connectionCreator = new ConnectionCreator();
    private int poolCapacity;

    private ProxyConnectionPool() {
        readPoolCapacityFromProperties();
        connections = new ArrayDeque<>();
        semaphore = new Semaphore(poolCapacity);
        createConnections();
    }

    public static ProxyConnectionPool getInstance() {
        if (!initialized.get()) {
            try {
                initializationLock.lock();
                if (!initialized.get()) {
                    instance = new ProxyConnectionPool();
                    initialized.set(true);
                }
            } finally {
                initializationLock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException();
        } finally {
            connectionLock.unlock();
        }
    }

    public void closeConnection(ProxyConnection connection) {
        try {
            connection.setAutoCommit(true);
            reconnectionLock.lock();
            connections.push(connection);
            semaphore.release();
        } catch (SQLException e) {
            LOGGER.error(CANT_ENABLE_AUTO_COMMIT, e);
        } finally {
            reconnectionLock.unlock();
        }
    }

    private void readPoolCapacityFromProperties() {
        try {
            Class<? extends ProxyConnectionPool> thisClass = this.getClass();
            ClassLoader classLoader = thisClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FILE_NAME);
            Properties property = new Properties();
            property.load(inputStream);
            poolCapacity = Integer.parseInt(property.getProperty(POOL_CAPACITY_PROPERTY));
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException(FILE_NOT_FOUND + e.getMessage(), e);
        }
    }

    private void createConnections() {
        for (int i = 0; i < poolCapacity; i++) {
            ProxyConnection connection = connectionCreator.createConnection();
            connections.push(connection);
        }
        if (connections.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_POOL);
        }
    }
}
