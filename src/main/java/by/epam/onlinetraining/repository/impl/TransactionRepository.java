package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.impl.TransactionBuilder;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionRepository extends AbstractRepository<Transaction> {
    private static final String TABLE_NAME = "transactions";
    private static final String SELECT_QUERY = "SELECT * FROM ";//TODO * && FROM transactions
    private static final String ID = "id";
    private static final String PAYER_ID = "payer_id";
    private static final String DATE = "date";
    private static final String OPERATION_TYPE_ID = "operation_type_id";
    private static final String SUM = "sum";

    public TransactionRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Transaction> query(SqlSpecification specification) throws RepositoryException {
        return Optional.empty();
    }

    @Override
    public List<Transaction> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + getTableName() + specification.toSql();
        List<Object> params = specification.getParameters();
        return executeQuery(query, new TransactionBuilder(), params);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map<String, Object> getFields(Transaction item) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(PAYER_ID, item.getPayerId());
        values.put(DATE, item.getDate());
        values.put(OPERATION_TYPE_ID, item.getOperationType());
        values.put(SUM, item.getSum());
        values.put(ID, item.getId());
        return values;
    }
}
