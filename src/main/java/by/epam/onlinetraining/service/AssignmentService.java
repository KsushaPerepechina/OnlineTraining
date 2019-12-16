package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.exception.ServiceException;

import java.util.List;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.Assignment} type.
 */
public interface AssignmentService {

    /**
     * The method searches for tasks assigned by mentor to a given training.
     *
     * @param trainingId training identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Assignment} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Assignment> findByTrainingId(int trainingId) throws ServiceException;

    /**
     * The method searches for assignments selected by the student for a given consultation.
     *
     * @param consultationId consultation identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Assignment} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Assignment> findByConsultationId(int consultationId) throws ServiceException;

    /**
     * The method for adding new assignment.
     *
     * @param assignment an {@link by.epam.onlinetraining.entity.Assignment} object, that contains assignment data to be added.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void add(Assignment assignment) throws ServiceException;

    /**
     * The method removes assignment with given identifier from repository.
     *
     * @param id training identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void delete(int id) throws ServiceException;
}
