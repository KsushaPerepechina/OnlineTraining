package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.ConsultationStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Designed to build an object of type {@link by.epam.onlinetraining.entity.Consultation} with specified characteristics.
 */
public class ConsultationBuilder implements EntityBuilder<Consultation> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "consultations.id";
    private static final String DATE = "date";
    private static final String COST = "cost";
    private static final String STATUS = "status";
    private static final String PERFORMANCE = "performance";
    private static final String QUALITY = "quality";
    private static final String PAYED = "payed";
    private static UserBuilder userBuilder = new UserBuilder();
    private static TrainingBuilder trainingBuilder = new TrainingBuilder();

    /**
     * Builds an object of type Consultation with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object of type Consultation.
     * @return Returns built object of Consultation type.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    @Override
    public Consultation build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            Date sqlDate = resultSet.getDate(DATE);
            LocalDate date = null;
            if (sqlDate != null) {
                date = sqlDate.toLocalDate();
            }
            BigDecimal cost = resultSet.getBigDecimal(COST);
            ConsultationStatus status = ConsultationStatus.valueOf(resultSet.getString(STATUS).toUpperCase());
            int performance = resultSet.getInt(PERFORMANCE);
            int quality = resultSet.getInt(QUALITY);
            User student = userBuilder.buildStudent(resultSet);
            Training training = trainingBuilder.buildRepresentation(resultSet);
            boolean payed = resultSet.getBoolean(PAYED);
            return new Consultation(id, student, training, date, cost, status, performance, quality, payed);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    /**
     * Builds a short representation of an object of type Consultation with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object of type Consultation.
     * @return Returns built object of Consultation type.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    public Consultation buildRepresentation(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            return new Consultation(id);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
