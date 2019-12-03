package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.AssignmentBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AssignmentRepository extends AbstractRepository<Assignment> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TABLE_NAME = "assignments";
    private static final String SELECT_QUERY = "SELECT assignments.id, name, type, training_id FROM assignments ";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String TRAINING_ID = "training_id";

    public AssignmentRepository(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Assignment assignment) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, assignment.getName());
        values.put(TYPE, assignment.getType().toString().toLowerCase());
        values.put(TRAINING_ID, assignment.getTrainingId());
        values.put(ID, assignment.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Assignment> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Assignment> builder = new AssignmentBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Assignment> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Assignment> builder = new AssignmentBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
