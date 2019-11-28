package by.epam.onlinetraining.utils;

import by.epam.onlinetraining.database.ProxyConnectionPool;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.repository.impl.*;

public class RepositoryCreator implements AutoCloseable {
    private ProxyConnectionPool proxyConnectionPool;
    private ProxyConnection connection;

    public RepositoryCreator() {
        proxyConnectionPool = ProxyConnectionPool.getInstance();
        connection = proxyConnectionPool.getConnection();
    }

    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }

    public TrainingRepository getTrainingRepository() {
        return new TrainingRepository(connection);
    }

    public AssignmentRepository getTaskRepository() {
        return new AssignmentRepository(connection);
    }

    public ConsultationRepository getConsultationRepository() {
        return new ConsultationRepository(connection);
    }

    public TransactionRepository getTransactionRepository() {
        return new TransactionRepository(connection);
    }

    @Override
    public void close() {
        proxyConnectionPool.closeConnection(connection);
    }
}