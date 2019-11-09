package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.TopicBuilder;
import by.epam.onlinetraining.entity.Topic;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TopicRepository extends AbstractRepository<Topic> {
    private static final String TABLE_NAME = "topics";
    private static final String SELECT_QUERY = "SELECT * FROM topics ";//TODO *
    private static final String ID = "id";
    private static final String NAME = "name";

    public TopicRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Topic topic) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, topic.getName());
        values.put(ID, topic.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Topic> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Topic> builder = new TopicBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Topic> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Topic> builder = new TopicBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
