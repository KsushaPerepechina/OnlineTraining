package by.epam.onlinetraining.builder;

import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.ResultSet;

/**
 * Designed to determine the behavior of the implementing class in the form of construction
 * an object of type T with specified characteristics.
 *
 * @param <Entity> - type of object.
 */
public interface EntityBuilder<Entity> {
    /**
     * Builds an object of type Entity with properties.
     *
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type Entity.
     * @return Returns built object type Entity.
     * @throws RepositoryException Throws when {@link java.sql.SQLException} is caught.
     */
    Entity build(ResultSet resultSet) throws RepositoryException;
}
