package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.type.AssignmentType;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignmentBuilder implements EntityBuilder<Assignment> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "assignments.id";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String TRAINING_ID = "training_id";

    @Override
    public Assignment build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            AssignmentType type = AssignmentType.valueOf(resultSet.getString(TYPE).toUpperCase());
            int trainingId = resultSet.getInt(TRAINING_ID);
            return new Assignment(id, name, type, trainingId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
