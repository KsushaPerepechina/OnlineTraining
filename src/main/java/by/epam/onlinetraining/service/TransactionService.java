package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.ServiceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.Transaction} type.
 */
public interface TransactionService {

    /**
     * The method searches for transactions for user with given identifier.
     *
     * @param id a {@link Integer} object identifier in database.
     * @return a {@link List} implementation with {@link Transaction} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Transaction> findByPayerId(int id) throws ServiceException;

    /**
     * The method adds operation of defined type.
     *
     * @param payerId a {@link java.lang.Integer} object client identifier in database
     * @param date a {@link java.time.LocalDate} object that maps date of operation.
     * @param sum a {@link java.math.BigDecimal} object that maps sum of operation.
     * @param operationType a {@link by.epam.onlinetraining.entity.type.OperationType} object that maps operation type.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void addOperation(int payerId, OperationType operationType, LocalDate date, BigDecimal sum) throws ServiceException;
}
