package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.UserBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository extends AbstractRepository<User> {
    private static final String TABLE_NAME = "users";
    private static final String SELECT_QUERY = "SELECT id, first_name, last_name, birth_date, email, phone_number, " +
            "password, blocking_status, role, balance FROM users ";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTH_DATE = "birth_date";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String PASSWORD = "password";
    private static final String BLOCKING_STATUS = "blocking_status";
    private static final String ROLE = "role";
    private static final String BALANCE = "balance";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    public UserRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(User user) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(FIRST_NAME, user.getFirstName());
        values.put(LAST_NAME, user.getLastName());
        values.put(BIRTH_DATE, user.getBirthDate());
        values.put(EMAIL, user.getEmail());
        values.put(PHONE_NUMBER, user.getPhoneNumber());
        values.put(PASSWORD, user.getPassword());
        BlockingStatus blockingStatus = user.getBlockingStatus();
        if(blockingStatus != null) {
            String stringBlockingStatus = blockingStatus.toString().toLowerCase();
            values.put(BLOCKING_STATUS, stringBlockingStatus);
        }
        UserRole role = user.getRole();
        if(role != null) {
            String stringRole = role.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
            values.put(ROLE, stringRole);
        }
        values.put(BALANCE, user.getBalance());
        values.put(ID, user.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<User> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<User> builder = new UserBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<User> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<User> builder = new UserBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
