package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.TrainingBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingRepository extends AbstractRepository<Training> {
    private static final String TABLE_NAME = "trainings";
    private static final String SELECT_QUERY = "SELECT trainings.id, name, start_date, end_date, progress, users.id, " +
            "users.first_name, users.last_name FROM trainings JOIN users ON trainings.mentor_id = users.id ";
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
    public Map<String, Object> getFields(Training training) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, training.getName());
        values.put(START_DATE, training.getStartDate());
        values.put(END_DATE, training.getEndDate());
        if (training.getProgress() == null) {
            values.put(PROGRESS, null);
        } else {
            values.put(PROGRESS, training.getProgress().toString().toLowerCase().replace(UNDERSCORE_SYMBOL, SPACE_CHAR));
        }
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
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Training> builder = new TrainingBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
