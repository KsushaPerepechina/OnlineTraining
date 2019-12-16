package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.Training} type.
 */
public interface TrainingService {

    /**
     * The method searches for training with given identifier.
     *
     * @param id training identifier in repository.
     * @return a {@link java.util.Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    Optional<Training> findById(int id) throws ServiceException;

    /**
     * The method searches for training with given mentor id.
     *
     * @param mentorId mentor identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Training} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Training> findByMentorId(int mentorId) throws ServiceException;

    /**
     * The method searches for training with given progress.
     *
     * @param progress a {@link by.epam.onlinetraining.entity.type.TrainingProgress} object contains the progress
     *                 of trainings retrieved from the repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Training} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Training> findByProgress(TrainingProgress progress) throws ServiceException;

    /**
     * The method searches for training with given progress and mentor id.
     *
     * @param progress a {@link by.epam.onlinetraining.entity.type.TrainingProgress} object contains the progress
     *                 of trainings retrieved from the repository.
     * @param mentorId mentor identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Training} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Training> findByProgressAndMentorId(TrainingProgress progress, int mentorId) throws ServiceException;

    /**
     * The method for updating training information with given parameters.
     *
     * @param trainingData a {@link java.util.Map} object, that contains training data.
     * @param language a {@link java.lang.String} object, that contains the language of the processed data
     *                necessary for the correct processing of dates.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void update(Map<String, String> trainingData, String language) throws ServiceException;

    /**
     * The method removes training with given identifier from repository.
     *
     * @param id training identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void delete(int id) throws ServiceException;
}
