package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.StudentRecord;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainingBuilder implements EntityBuilder<Training> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "pk_id";
    private static final String NAME = "name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String PROGRESS = "progress";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";
    private static UserBuilder userBuilder = new UserBuilder();
    private static StudentRecordBuilder recordBuilder = new StudentRecordBuilder();

    @Override
    public Training build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            Date startDate = resultSet.getDate(START_DATE);
            Date endDate = resultSet.getDate(END_DATE);
            TrainingProgress progress = TrainingProgress.valueOf(resultSet.getString(PROGRESS)
                    .toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            User mentor = userBuilder.buildByRole(resultSet, true);
            List<StudentRecord> students = new ArrayList<>();
            if (resultSet.getInt("s.id") != 0) {
                students.add(recordBuilder.build(resultSet));
                while (resultSet.next()) {
                    if (resultSet.getInt(ID) == id) {
                        students.add(recordBuilder.build(resultSet));
                    } else {
                        resultSet.previous();
                        break;
                    }
                }
            }
            return new Training(id, name, startDate, endDate, progress, mentor, students);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
