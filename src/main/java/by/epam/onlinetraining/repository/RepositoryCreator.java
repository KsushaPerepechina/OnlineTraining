package by.epam.onlinetraining.repository;

import by.epam.onlinetraining.database.ProxyConnectionPool;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.repository.impl.*;

/**
 * Provides {@link java.lang.AutoCloseable} creator of repository implementation class with connection to database for each.
 */
public class RepositoryCreator implements AutoCloseable {
    private ProxyConnectionPool proxyConnectionPool;
    private ProxyConnection connection;

    public RepositoryCreator() {
        proxyConnectionPool = ProxyConnectionPool.getInstance();
        connection = proxyConnectionPool.getConnection();
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.UserRepository} object with connection to database.
     */
    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.TrainingRepository} object with connection to database.
     */
    public TrainingRepository getTrainingRepository() {
        return new TrainingRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.AssignmentRepository} object with connection to database.
     */
    public AssignmentRepository getAssignmentRepository() {
        return new AssignmentRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.ConsultationRepository} object with connection to database.
     */
    public ConsultationRepository getConsultationRepository() {
        return new ConsultationRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.TrainingRepository} object with connection to database.
     */
    public TransactionRepository getTransactionRepository() {
        return new TransactionRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.RecordRepository} object with connection to database.
     */
    public RecordRepository getRecordRepository() {
        return new RecordRepository(connection);
    }

    /**
     * @return an {@link by.epam.onlinetraining.repository.impl.ConsultationRepository} object with connection to database.
     */
    public ConsultationAssignmentRepository getConsultationAssignmentRepository() {
        return new ConsultationAssignmentRepository(connection);
    }

    /**
     *  Returns database connection to {@link by.epam.onlinetraining.database.ProxyConnectionPool}
     */
    @Override
    public void close() {
        proxyConnectionPool.closeConnection(connection);
    }
}