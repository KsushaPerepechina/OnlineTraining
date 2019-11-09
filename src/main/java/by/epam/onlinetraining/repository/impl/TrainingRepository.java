package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.TrainingBuilder;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingRepository extends AbstractRepository<Training> {
    private static final String TABLE_NAME = "trainings";
    private static final String SELECT_QUERY = "SELECT * FROM trainings ";//TODO *
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    private static final String PROGRESS = "progress";

    public TrainingRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Training training) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, training.getName());
        values.put(START_DATE, training.getStartDate());
        values.put(END_DATE, training.getEndDate());
        values.put(PROGRESS, training.getProgress());
        values.put(ID, training.getId());
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
