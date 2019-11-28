package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.ConsultationBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConsultationRepository extends AbstractRepository<Consultation> {
    private static final String TABLE_NAME = "consultations";
    private static final String SELECT_QUERY = "SELECT id AS pk_id, student_id, mentor_id, date_time, cost, mark, quality " +
            "FROM consultations ";
    private static final String ID = "id";
    private static final String STUDENT_ID = "student_id";
    private static final String MENTOR_ID = "mentor_id";
    private static final String DATE_TIME = "date_time";
    private static final String COST = "cost";
    private static final String MARK = "mark";
    private static final String QUALITY = "quality";

    public ConsultationRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Consultation consultation) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(STUDENT_ID, consultation.getStudentId());
        values.put(MENTOR_ID, consultation.getMentorId());
        values.put(DATE_TIME, consultation.getDateTime());
        values.put(COST, consultation.getCost());
        values.put(MARK, consultation.getMark());
        values.put(QUALITY, consultation.getQuality());
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
