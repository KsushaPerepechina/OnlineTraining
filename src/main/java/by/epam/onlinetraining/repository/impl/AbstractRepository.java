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

/**
 * This class provides a skeletal implementation of the {@link by.epam.onlinetraining.repository.Repository} interface,
 * to minimize the effort required to implement this interface.
 */
public abstract class AbstractRepository<T extends Entity> implements Repository<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    private ProxyConnection connection;

    AbstractRepository(ProxyConnection connection) {
        this.connection = connection;
    }

    /**
     * Performs a parameterized read query to a database with parameters, waiting for a set of objects,
     * and builds them with the help of a concrete builder implementation.
     *
     * @param query a {@link java.lang.String} object that contains database query
     * @param builder a implementation of {@link by.epam.onlinetraining.builder.EntityBuilder} with a concrete class
     *               whose objects are to be built.
     * @param params a {@link java.util.List} List of objects that contains parameters that should be substituted in query.
     * @return a {@link java.util.List} implementation with objects.
     * @throws RepositoryException Signals that an database access object exception of some sort has occurred.
     */
    List<T> executeQuery(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> objects = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            QueryHelper.prepare(preparedStatement, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T item = builder.build(resultSet);
                objects.add(item);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RepositoryException(e.getMessage(), e);
        }
        return objects;
    }

    /**
     * Performs a parameterized read query to a database with parameters, waiting for an object,
     * and builds them with the help of a concrete builder implementation.
     *
     * @param query a {@link java.lang.String} object that contains database query.
     * @param builder a implementation of {@link by.epam.onlinetraining.builder.EntityBuilder} with a concrete class
     *                whose object is to be built.
     * @param params a {@link java.util.List} List of objects that contains parameters that should be substituted in query.
     * @return a {@link java.util.Optional} implementation with object.
     * @throws RepositoryException Signals that an database access object exception of some sort has occurred.
     */
    Optional<T> executeQueryForSingleResult(String query, EntityBuilder<T> builder, List<Object> params) throws RepositoryException {
        List<T> items = executeQuery(query, builder, params);
        return items.size() == 1 ?
                Optional.of(items.get(0)) :
                Optional.empty();
    }

    /**
     * Performs update query to a database with parameters or insert new item to a database .
     *
     * @param item a {@link T} object that updates or inserts in database.
     * @throws RepositoryException Signals that an database access object exception of some sort has occurred.
     */
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

    /**
     * Performs update query to a database with parameters for removing item from the database.
     *
     * @param id identifier of an object that should be removed from database.
     * @throws RepositoryException Signals that an database access object exception of some sort has occurred.
     */
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
