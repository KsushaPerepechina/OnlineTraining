package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.RepositoryException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserBuilder implements EntityBuilder<User> {
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTH_DATE = "birth_date";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String BLOCKING_STATUS = "blocking_status";
    private static final String ROLE = "role";
    private static final String BALANCE = "balance";

    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            Date birthDate = resultSet.getDate(BIRTH_DATE);
            String email = resultSet.getString(EMAIL);
            String password = resultSet.getString(PASSWORD);
            boolean blocked = resultSet.getBoolean(BLOCKING_STATUS);
            UserRole role = UserRole.valueOf(resultSet.getString(ROLE).toUpperCase().replace(" ", "_"));//TODO
            BigDecimal balance = resultSet.getBigDecimal(BALANCE);
            return new User(id, firstName, lastName, birthDate, email, password, blocked, role, balance);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);//TODO message
        }
    }
}
