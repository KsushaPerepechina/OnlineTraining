package by.epam.onlinetraining.repository.impl;

import by.epam.onlinetraining.builder.EntityBuilder;
import by.epam.onlinetraining.database.ProxyConnection;
import by.epam.onlinetraining.entity.Entity;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.repository.Repository;
import by.epam.onlinetraining.util.QueryHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    private ProxyConnection connection;

    AbstractRepository(ProxyConnection connection) {
        this.connection = connection;
    }

    List<T> executeQuery(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            QueryHelper.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
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

    @Override
    public void save(T item) throws RepositoryException {
        try {
            String query;
            Map<String, Object> fields = getFields(item);
            String tableName = getTableName();
            if (item.getId() != null) {
                query = QueryHelper.formUpdateQuery(fields, tableName);
            } else {
                query = QueryHelper.formInsertQuery(fields, tableName);
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                List<Object> params = new ArrayList<>(fields.values());
                params = params.stream().filter(param -> !(param == null)).collect(Collectors.toList());
                QueryHelper.prepare(preparedStatement, params);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try {
            String query;
            String tableName = getTableName();
            query = QueryHelper.formDeleteQuery(tableName);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
    }
}
