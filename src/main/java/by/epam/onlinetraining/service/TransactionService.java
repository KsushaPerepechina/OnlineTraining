package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.TransactionRepository;
import by.epam.onlinetraining.specification.impl.transaction.FindByPayerIdSpecification;
import by.epam.onlinetraining.utils.RepositoryCreator;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class TransactionService {
    public List<Transaction> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            return transactionRepository.queryAll(new FindByPayerIdSpecification(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addOperations(int payerId, OperationType operationType,
                              Date date, BigDecimal sum) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            Transaction transaction = new Transaction(payerId, date, operationType, sum);
            transactionRepository.save(transaction);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
