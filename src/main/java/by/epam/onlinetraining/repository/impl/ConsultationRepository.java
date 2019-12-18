package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.ConsultationBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.ConsultationStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsultationRepository extends AbstractRepository<Consultation> {
    private static final String TABLE_NAME = "consultations";
    private static final String SELECT_QUERY = "SELECT consultations.id, date, cost, status, performance, quality, " +
            "payed, users.id, first_name, last_name, trainings.id, trainings.name, trainings.mentor_id FROM consultations " +
            "LEFT JOIN users ON consultations.student_id = users.id LEFT JOIN trainings ON consultations.training_id = " +
            "trainings.id ";
    private static final String ID = "id";
    private static final String STUDENT_ID = "student_id";
    private static final String TRAINING_ID = "training_id";
    private static final String DATE = "date";
    private static final String COST = "cost";
    private static final String STATUS = "status";
    private static final String PERFORMANCE = "performance";
    private static final String QUALITY = "quality";
    private static final String PAYED = "payed";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    public ConsultationRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Consultation consultation) {
        Map<String, Object> values = new LinkedHashMap<>();
        User student = consultation.getStudent();
        if (student != null) {
            values.put(STUDENT_ID, student.getId());
        }
        Training training = consultation.getTraining();
        if (student != null) {
            values.put(TRAINING_ID, training.getId());
        }
        values.put(DATE, consultation.getDate());
        values.put(COST, consultation.getCost());
        ConsultationStatus status = consultation.getStatus();
        if (status != null) {
            values.put(STATUS, status.toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR));
        }
        values.put(PERFORMANCE, consultation.getPerformance());
        values.put(QUALITY, consultation.getQuality());
        values.put(PAYED, consultation.isPayed());
        values.put(ID, consultation.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Consultation> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Consultation> builder = new ConsultationBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Consultation> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Consultation> builder = new ConsultationBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
