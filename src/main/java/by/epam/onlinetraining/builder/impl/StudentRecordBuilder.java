package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.StudentRecord;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRecordBuilder implements EntityBuilder<StudentRecord> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String STATUS = "th.status";
    private static final String MARK = "th.mark";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private static UserBuilder userBuilder = new UserBuilder();

    @Override
    public StudentRecord build(ResultSet resultSet) throws RepositoryException {
        try {
            StudentStatus status = StudentStatus.valueOf(resultSet.getString(STATUS)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            int mark = resultSet.getInt(MARK);
            User student = userBuilder.buildByRole(resultSet, false);
            return new StudentRecord(student, status, mark);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
