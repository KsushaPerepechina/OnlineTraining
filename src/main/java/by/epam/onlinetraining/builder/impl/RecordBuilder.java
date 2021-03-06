package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link by.epam.onlinetraining.entity.Record} with specified characteristics.
 */
public class RecordBuilder implements EntityBuilder<Record> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "records.id";
    private static final String STATUS = "status";
    private static final String MARK = "mark";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private static UserBuilder userBuilder = new UserBuilder();
    private static TrainingBuilder trainingBuilder = new TrainingBuilder();

    /**
     * Builds an object of type Record with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object of type Record.
     * @return Returns built object of Record type.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    @Override
    public Record build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt(ID);
            User student = userBuilder.buildStudent(resultSet);
            Training training = trainingBuilder.build(resultSet);
            StudentStatus status = StudentStatus.valueOf(resultSet.getString(STATUS)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            int mark = resultSet.getInt(MARK);
            return new Record(id, student, training, status, mark);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
