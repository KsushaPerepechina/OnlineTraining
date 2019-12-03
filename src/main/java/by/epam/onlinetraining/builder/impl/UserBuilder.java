package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserBuilder implements EntityBuilder<User> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "users.id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String BIRTH_DATE = "birth_date";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String BLOCKING_STATUS = "blocking_status";
    private static final String ROLE = "role";
    private static final String BALANCE = "balance";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    @Override
    public User build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            Date birthSqlDate = resultSet.getDate(BIRTH_DATE);
            LocalDate birthDate = null;
            if (birthSqlDate != null) {
                birthDate = birthSqlDate.toLocalDate();
            }
            String email = resultSet.getString(EMAIL);
            String phoneNumber = resultSet.getString(PHONE_NUMBER);
            BlockingStatus blockingStatus = BlockingStatus.valueOf(resultSet.getString(BLOCKING_STATUS).toUpperCase());
            UserRole role = UserRole.valueOf(resultSet.getString(ROLE)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            BigDecimal balance = resultSet.getBigDecimal(BALANCE);
            return new User(id, firstName, lastName, birthDate, email, phoneNumber, blockingStatus, role, balance);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
    public User buildRepresentation(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String firstName = resultSet.getString(FIRST_NAME);
            String lastName = resultSet.getString(LAST_NAME);
            return new User(id, firstName, lastName);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
