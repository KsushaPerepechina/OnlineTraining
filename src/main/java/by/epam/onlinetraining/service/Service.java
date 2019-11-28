package by.epam.onlinetraining.service;

import by.epam.onlinetraining.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface Service<T> {
    void create(Map<String, String> inputData) throws ServiceException;
    List<T> findAll() throws ServiceException;
    void update(Map<String, String> inputData) throws ServiceException;
    void delete(T t) throws ServiceException;
}
