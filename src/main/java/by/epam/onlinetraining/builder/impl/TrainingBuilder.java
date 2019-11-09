package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TrainingBuilder implements EntityBuilder<Training> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String PROGRESS = "progress";

    @Override
    public Training build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            Date startDate = resultSet.getDate(START_DATE);
            Date endDate = resultSet.getDate(END_DATE);
            TrainingProgress progress = TrainingProgress.valueOf(resultSet.getString(PROGRESS)
                    .toUpperCase().replace(" ", "_"));//TODO
            return new Training(id, name, startDate, endDate, progress);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
