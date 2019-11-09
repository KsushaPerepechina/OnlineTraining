package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.builder.impl.TaskBuilder;
import by.epam.onlinetraining.entity.Task;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TaskRepository extends AbstractRepository<Task> {
    private static final String TABLE_NAME = "tasks";
    private static final String SELECT_QUERY = "SELECT * FROM tasks ";//TODO *
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ASSIGNMENT = "assignment";
    private static final String DEADLINE = "deadline";
    private static final String STATE = "state_id";

    public TaskRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Map<String, Object> getFields(Task task) {
        Map<String, Object> values = new LinkedHashMap<>();
        values.put(NAME, task.getName());
        values.put(ASSIGNMENT, task.getAssignment());
        values.put(DEADLINE, task.getDeadline());
        values.put(STATE, task.getState());
        values.put(ID, task.getId());
        return values;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Optional<Task> query(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Task> builder = new TaskBuilder();
        List<Object> params = specification.getParameters();
        return executeQueryForSingleResult(query, builder, params);
    }

    @Override
    public List<Task> queryAll(SqlSpecification specification) throws RepositoryException {
        String query = SELECT_QUERY + specification.toSql();
        EntityBuilder<Task> builder = new TaskBuilder();
        List<Object> params = specification.getParameters();
        return executeQuery(query, builder, params);
    }
}
