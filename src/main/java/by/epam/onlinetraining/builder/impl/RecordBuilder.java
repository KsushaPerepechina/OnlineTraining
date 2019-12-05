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

public class RecordBuilder implements EntityBuilder<Record> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "records.id";
    private static final String STATUS = "status";
    private static final String MARK = "mark";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private static UserBuilder userBuilder = new UserBuilder();
    private static TrainingBuilder trainingBuilder = new TrainingBuilder();

    @Override
    public Record build(ResultSet resultSet) throws RepositoryException {
        try {
            Integer id = resultSet.getInt(ID);
            User student = userBuilder.buildRepresentation(resultSet);
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
