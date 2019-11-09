package by.epam.onlinetraining.builder;

import by.epam.onlinetraining.exception.RepositoryException;

import java.sql.ResultSet;

public interface EntityBuilder<T> {
    T build(ResultSet resultSet) throws RepositoryException;
}
