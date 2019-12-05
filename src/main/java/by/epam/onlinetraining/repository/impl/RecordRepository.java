package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.RecordBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecordRepository extends AbstractRepository<Record> {
    private static final String TABLE_NAME = "records";
    private static final String SELECT_QUERY = "SELECT records.id, mark, status, users.id, users.first_name, " +
            "users.last_name, trainings.id, trainings.name, trainings.start_date, trainings.end_date, " +
            "trainings.progress, trainings.mentor_id FROM records JOIN users ON records.student_id = users.id " +
            "JOIN trainings ON records.training_id = trainings.id ";
    private static final String ID = "id";
    private static final String STUDENT_ID = "student_id";
    private static final String TRAINING_ID = "training_id";
    private static final String MARK = "mark";
    private static final String STATUS = "status";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    public RecordRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Record record) {
        Map<String, Object> values = new LinkedHashMap<>();
        User student = record.getStudent();
        if (student != null) {
            values.put(STUDENT_ID, student.getId());
        }
        Training training = record.getTraining();
        if (training != null) {
            values.put(TRAINING_ID, training.getId());
        }
        values.put(MARK, record.getMark());
        String stringStatus = record.getStatus().toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        values.put(STATUS, stringStatus);
        values.put(ID, record.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Record> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Record> builder = new RecordBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Record> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Record> builder = new RecordBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
