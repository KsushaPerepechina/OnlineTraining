package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Task;
import by.epam.onlinetraining.entity.type.TaskState;
import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TaskBuilder implements EntityBuilder<Task> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ASSIGNMENT = "assignment";
    private static final String DEADLINE = "deadline";
    private static final String STATE_ID = "state_id";

    @Override
    public Task build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            Date assignment = resultSet.getDate(ASSIGNMENT);
            Date deadline = resultSet.getDate(DEADLINE);
            TaskState state = TaskState.valueOf(resultSet.getString(STATE_ID));//TODO
            return new Task(id, name, assignment, deadline, state);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
