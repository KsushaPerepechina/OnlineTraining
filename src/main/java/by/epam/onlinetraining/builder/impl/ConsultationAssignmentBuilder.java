package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.*;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Designed to build an object of type {@link by.epam.onlinetraining.entity.ConsultationAssignment} with specified characteristics.
 */
public class ConsultationAssignmentBuilder implements EntityBuilder<ConsultationAssignment> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "consultation_assignments.id";
    private static ConsultationBuilder consultationBuilder = new ConsultationBuilder();
    private static AssignmentBuilder assignmentBuilder = new AssignmentBuilder();

    /**
     * Builds an object of type ConsultationAssignment with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object of type ConsultationAssignment.
     * @return Returns built object of ConsultationAssignment type.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    @Override
    public ConsultationAssignment build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            Consultation consultation = consultationBuilder.buildRepresentation(resultSet);
            Assignment assignment = assignmentBuilder.build(resultSet);
            return new ConsultationAssignment(id, consultation, assignment);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
