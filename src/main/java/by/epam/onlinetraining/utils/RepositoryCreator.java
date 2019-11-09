package by.epam.onlinetraining.utils;

import by.epam.onlinetraining.database.ConnectionPool;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.repository.impl.*;

public class RepositoryCreator implements AutoCloseable {
    private ConnectionPool connectionPool;
    private ProxyConnection connection;

    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }

    public TrainingRepository getTrainingRepository() {
        return new TrainingRepository(connection);
    }

    public TaskRepository getTaskRepository() {
        return new TaskRepository(connection);
    }

    public TopicRepository getTopicRepository() {
        return new TopicRepository(connection);
    }

    public ConsultationRepository getConsultationRepository() {
        return new ConsultationRepository(connection);
    }

    public TransactionRepository getTransactionRepository() {
        return new TransactionRepository(connection);
    }

    @Override
    public void close() {
        connectionPool.closeConnection(connection);
    }
}
