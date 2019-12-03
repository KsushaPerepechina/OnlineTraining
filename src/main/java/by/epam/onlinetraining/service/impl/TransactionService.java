package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.TransactionRepository;
import by.epam.onlinetraining.specification.impl.transaction.FindByPayerIdSpecification;
import by.epam.onlinetraining.util.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TransactionService {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Transaction> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            return transactionRepository.queryAll(new FindByPayerIdSpecification(id));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addOperation(int payerId, OperationType operationType,
                             LocalDate date, BigDecimal sum) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            Transaction transaction = new Transaction(null, payerId, date, operationType, sum);
            transactionRepository.save(transaction);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
