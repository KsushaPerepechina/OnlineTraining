package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainingBuilder implements EntityBuilder<Training> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "trainings.id";
    private static final String NAME = "name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String PROGRESS = "progress";
    private static final String MENTOR_ID = "mentor_id";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    @Override
    public Training build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            LocalDate startDate = resultSet.getDate(START_DATE).toLocalDate();
            LocalDate endDate = resultSet.getDate(END_DATE).toLocalDate();
            TrainingProgress progress = TrainingProgress.valueOf(resultSet.getString(PROGRESS)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            UserBuilder userBuilder = new UserBuilder();
            User mentor = userBuilder.buildRepresentation(resultSet);
            return new Training(id, name, startDate, endDate, progress, mentor);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public Training buildRepresentation(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
//            TrainingProgress progress = TrainingProgress.valueOf(resultSet.getString(PROGRESS)
//                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));//TODO
            int mentorId = resultSet.getInt(MENTOR_ID);
            User mentor = new User(mentorId);
            //return new Training(id, progress, mentor);
            return new Training(id, mentor);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
