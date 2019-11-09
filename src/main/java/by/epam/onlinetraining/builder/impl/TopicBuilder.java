package by.epam.onlinetraining.builder.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Topic;
import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicBuilder implements EntityBuilder<Topic> {
    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Topic build(ResultSet resultSet) throws RepositoryException {
        try {
            int id = resultSet.getInt(ID);
            String name = resultSet.getString(NAME);
            return new Topic(id, name);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
