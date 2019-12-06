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

public class ConsultationBuilder implements EntityBuilder<Consultation> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "consultations.id";
    private static final String DATE_TIME = "date_time";
    private static final String COST = "cost";
    private static final String STATUS = "status";
    private static final String PERFORMANCE = "performance";
    private static final String QUALITY = "quality";
    private static UserBuilder userBuilder = new UserBuilder();
    private static TrainingBuilder trainingBuilder = new TrainingBuilder();

    @Override
    public Consultation build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            Date sqlDateTime = resultSet.getDate(DATE_TIME);
            LocalDate dateTime = null;
            if (sqlDateTime != null) {
                dateTime = sqlDateTime.toLocalDate();
            }
            BigDecimal cost = resultSet.getBigDecimal(COST);
            ConsultationStatus status = ConsultationStatus.valueOf(resultSet.getString(STATUS).toUpperCase());
            int performance = resultSet.getInt(PERFORMANCE);
            int quality = resultSet.getInt(QUALITY);
            User student = userBuilder.buildStudent(resultSet);
            Training training = trainingBuilder.buildRepresentation(resultSet);
            return new Consultation(id, student, training, dateTime, cost, status, performance, quality);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
