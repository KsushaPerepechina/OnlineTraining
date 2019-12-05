package by.epam.onlinetraining.builder;

import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.ResultSet;

/**
 * Designed for constructing entity objects from the resulting database selection.
 * @param <Entity> - object type.
 */
public interface EntityBuilder<Entity> {
    /**
     * Builds an object of type T with properties.
     * @param resultSet Instance of {@link java.sql.ResultSet} with property set to build an object type T.
     * @return Returns built object type T.
     * @throws RepositoryException Throws when SQL Exception is caught.
     */
    Entity build(ResultSet resultSet) throws RepositoryException;
}
