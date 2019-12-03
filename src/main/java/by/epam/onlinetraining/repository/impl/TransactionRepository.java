package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.impl.TransactionBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransactionRepository extends AbstractRepository<Transaction> {
    private static final String TABLE_NAME = "transactions";
    private static final String SELECT_QUERY = "SELECT id, payer_id, date, operation_type, sum " +
            "FROM transactions ";
    private static final String ID = "id";
    private static final String PAYER_ID = "payer_id";
    private static final String DATE = "date";
    private static final String OPERATION_TYPE = "operation_type";
    private static final String SUM = "sum";

    public TransactionRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Transaction transaction) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(PAYER_ID, transaction.getPayerId());
        values.put(DATE, transaction.getDate());
        values.put(OPERATION_TYPE, transaction.getOperationType().toString().toLowerCase());
        values.put(SUM, transaction.getSum());
        values.put(ID, transaction.getId());
        return values;
    }

    @Override
    public Optional<Transaction> query(SqlSpecification specification) {
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
}
