package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.AssignmentType;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link by.epam.onlinetraining.entity.Assignment} with specified characteristics.
 */
public class AssignmentBuilder implements EntityBuilder<Assignment> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "assignments.id";
    private static final String NAME = "assignments.name";
    private static final String TYPE = "type";
    private static TrainingBuilder trainingBuilder = new TrainingBuilder();

    /**
     * Builds an object of type Assignment with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object of type Assignment.
     * @return Returns built object type Assignment.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    @Override
    public Assignment build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            AssignmentType type = AssignmentType.valueOf(resultSet.getString(TYPE).toUpperCase());
            Training training = trainingBuilder.buildRepresentation(resultSet);
            return new Assignment(id, name, type, training);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
