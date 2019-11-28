package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionBuilder implements EntityBuilder<Transaction> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "pk_id";
    private static final String PAYER_ID = "payer_id";
    private static final String DATE = "date";
    private static final String OPERATION_TYPE = "operation_type";
    private static final String SUM = "sum";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    @Override
    public Transaction build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            int payerId = resultSet.getInt(PAYER_ID);
            Date date = resultSet.getDate(DATE);
            OperationType operationType = OperationType.valueOf(resultSet.getString(OPERATION_TYPE)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            BigDecimal sum = resultSet.getBigDecimal(SUM);
            return new Transaction(id, payerId, date, operationType, sum);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
