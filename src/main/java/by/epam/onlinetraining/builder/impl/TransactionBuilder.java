package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.exception.RepositoryException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransactionBuilder implements EntityBuilder<Transaction> {
    private static final String ID = "id";
    private static final String PAYER_ID = "payer_id";
    private static final String DATE = "date";
    private static final String OPERATION_TYPE_ID = "operation_type_id";
    private static final String SUM = "sum";

    @Override
    public Transaction build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            int payerId = resultSet.getInt(PAYER_ID);
            Date date = resultSet.getDate(DATE);
            OperationType operationType = OperationType.valueOf(resultSet.getString(OPERATION_TYPE_ID)
                    .toUpperCase().replace(" ", "_"));//TODO replace with const && form type from id
            BigDecimal sum = resultSet.getBigDecimal(SUM);
            return new Transaction(id, payerId, date, operationType, sum);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
