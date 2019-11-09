package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.entity.Entity;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.repository.Repository;
import by.epam.onlinetraining.utils.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    private Connection connection;//1 коннекшн на все запросы пользователя

    AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    List<T> executeQuery(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            QueryHelper.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
        return objects;
    }

    Optional<T> executeQueryForSingleResult(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> items = executeQuery(query, builder, params);
        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    public void save(T item) throws RepositoryException {
        try {
            String query;
            Map<String, Object> fields = getFields(item);
            String tableName = getTableName();
            if (item.getId() != 0) {
                query = QueryHelper.formUpdateQuery(fields, tableName);
            } else {
                query = QueryHelper.formInsertQuery(fields, tableName);
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            QueryHelper.prepare(preparedStatement, fields);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
