package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.RepositoryException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConsultationBuilder implements EntityBuilder<Consultation> {
    private static final String ID = "id";
    private static final String STUDENT_ID = "student_id";
    private static final String MENTOR_ID = "mentor_id";
    private static final String DATE_TIME = "date_time";
    private static final String COST = "cost";
    private static final String MARK = "mark";
    private static final String QUALITY = "quality";

    @Override
    public Consultation build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            int studentId = resultSet.getInt(STUDENT_ID);
            int mentorId = resultSet.getInt(MENTOR_ID);
            Date dateTime = resultSet.getDate(DATE_TIME);//TODO
            BigDecimal cost = resultSet.getBigDecimal(COST);
            int mark = resultSet.getInt(MARK);
            int quality = resultSet.getInt(QUALITY);
            return new Consultation(id, studentId, mentorId, dateTime, cost, mark, quality);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);//TODO message
        }
    }
}
