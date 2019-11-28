package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.TrainingBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingRepository extends AbstractRepository<Training> {
    private static final String TABLE_NAME = "t";
    private static final String SELECT_QUERY = "SELECT t.id AS pk_id, name, start_date, end_date, progress, " +
            "m.id, m.first_name, m.last_name, mark, status, s.id, s.first_name, s.last_name FROM trainings AS t " +
            "JOIN users AS m ON t.mentor_id = m.id LEFT JOIN student_training_history AS th ON t.id = th.training_id " +
            "LEFT JOIN users AS s ON th.student_id = s.id ";
    private static final String ORDER_BY = " ORDER BY pk_id";
    private static final String TRAINING_ID = "id";
    private static final String NAME = "name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String PROGRESS = "progress";
    private static final String MENTOR_ID = "mentor_id";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    public TrainingRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Training training) {//TODO
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, training.getName());
        values.put(START_DATE, training.getStartDate());
        values.put(END_DATE, training.getEndDate());
        String stringProgress = training.getProgress().toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR);
        values.put(PROGRESS, stringProgress);
        values.put(MENTOR_ID, training.getMentor().getId());
        values.put(TRAINING_ID, training.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Training> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Training> builder = new TrainingBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Training> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql() + ORDER_BY;
        EntityBuilder<Training> builder = new TrainingBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
