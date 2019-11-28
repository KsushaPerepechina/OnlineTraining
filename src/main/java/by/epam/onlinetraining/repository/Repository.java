package by.epam.onlinetraining.repository;

import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.specification.SqlSpecification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> query(SqlSpecification specification) throws RepositoryException;

    List<T> queryAll(SqlSpecification specification) throws RepositoryException;

    void save(T item) throws RepositoryException;

    String getTableName();

    Map<String, Object> getFields(T item);

    void remove(int id) throws RepositoryException;
}
