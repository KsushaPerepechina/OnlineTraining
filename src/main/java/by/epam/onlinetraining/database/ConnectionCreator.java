package by.epam.onlinetraining.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Designed for creation database connection from properties.
 */
public class ConnectionCreator {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String RESOURCE_FILE = "db.properties";
    private static final String URL = "db.url";
    private static final String NAME = "db.name";
    private static final String PASSWORD = "db.password";
    private static final String DRIVER = "db.driver";
    private static final String DRIVER_NOT_FOUND = "Driver not found";
    private static final String FILE_NOT_FOUND = "File not found";

    /**
     * Establishes a connection to the database based on the parameters extracted from the properties file.
     *
     * @return a {@link by.epam.onlinetraining.database.ProxyConnection} object that provides connection to database
     */
    public ProxyConnection createConnection() {
        try {
            Class<? extends ConnectionCreator> thisClass = this.getClass();
            ClassLoader classLoader = thisClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FILE);
            Properties property = new Properties();
            property.load(inputStream);
            String url = property.getProperty(URL);
            String name = property.getProperty(NAME);
            String password = property.getProperty(PASSWORD);
            String driver = property.getProperty(DRIVER);
            Class.forName(driver);
            return new ProxyConnection(DriverManager.getConnection(url, name, password));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException();
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException(DRIVER_NOT_FOUND + e.getMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new IllegalArgumentException(FILE_NOT_FOUND + e.getMessage(), e);
        }
    }
}
