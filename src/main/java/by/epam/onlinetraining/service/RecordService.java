package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.ServiceException;

import java.util.List;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.Record} type.
 */
public interface RecordService {

    /**
     * The method for updating request status of student.
     *
     * @param recordId record identifier in repository.
     * @param status a {@link by.epam.onlinetraining.entity.type.StudentStatus} object contains training status.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void updateStudentStatus(int recordId, StudentStatus status) throws ServiceException;

    /**
     * The method searches for records with given training.
     *
     * @param trainingId training identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Record} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Record> findByTrainingId(int trainingId) throws ServiceException;

    /**
     * The method searches for records with given training and status.
     *
     * @param trainingId training identifier in repository.
     * @param status a {@link by.epam.onlinetraining.entity.type.StudentStatus} object contains training status.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Record} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Record> findByTrainingIdAndStatus(int trainingId, StudentStatus status) throws ServiceException;

    /**
     * The method searches for records with given student.
     *
     * @param studentId student identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Record} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Record> findByStudentId(int studentId) throws ServiceException;

    /**
     * The method searches for trainings with given student.
     *
     * @param studentId student identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Training} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Training> findStudentTrainings(int studentId) throws ServiceException;

    /**
     * The method searches for trainings with given student and status.
     *
     * @param studentId student identifier in repository.
     * @param status a {@link by.epam.onlinetraining.entity.type.StudentStatus} object contains training status.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Training} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Training> findStudentTrainingsByStatus(int studentId, StudentStatus status) throws ServiceException;

    /**
     * Method for setting the final grade to the student upon completion of the course.
     *
     * @param recordId record identifier in repository.
     * @param mark student's final grade.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void rateStudent(int recordId, int mark) throws ServiceException;

    /**
     * Method for applying for training.
     *
     * @param studentId student identifier in repository.
     * @param trainingId training identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void applyForTraining(int studentId, int trainingId) throws ServiceException;
}
