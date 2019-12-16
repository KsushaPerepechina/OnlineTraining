package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.Consultation} type.
 */
public interface ConsultationService {

    /**
     * The method searches for consultation with given identifier.
     *
     * @param id consultation identifier in repository.
     * @return a {@link java.util.Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    Optional<Consultation> findById(int id) throws ServiceException;

    /**
     * The method searches for consultations scheduled or requested to a given training.
     *
     * @param trainingId training identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Consultation} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Consultation> findByTrainingId(int trainingId) throws ServiceException;

    /**
     * The method searches for consultations scheduled or requested for a given student.
     *
     * @param studentId student identifier in repository.
     * @return a {@link java.util.List} implementation with {@link by.epam.onlinetraining.entity.Consultation} objects.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<Consultation> findByStudentId(int studentId) throws ServiceException;

    /**
     * Method for evaluating the quality of student counseling.
     *
     * @param consultationId consultation identifier in repository.
     * @param quality grade of quality.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void rateConsultationQuality(int consultationId, int quality) throws ServiceException;

    /**
     * Method for assessing student's current academic performance by mentor.
     *
     * @param consultationId consultation identifier in repository.
     * @param currentPerformance grade of student's performance.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void rateStudentPerformance(int consultationId, int currentPerformance) throws ServiceException;

    /**
     * Method for applying for consultation.
     *
     * @param studentId student identifier in repository.
     * @param trainingId training identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void requestConsultation(int studentId, int trainingId) throws ServiceException;

    /**
     * Method for including assignment to consultation.
     *
     * @param assignmentId assignment identifier in repository.
     * @param consultationId consultation identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void includeAssignment(int assignmentId, int consultationId) throws ServiceException;

    /**
     * Method for excluding assignment from consultation.
     *
     * @param assignmentId assignment identifier in repository.
     * @param consultationId consultation identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void excludeAssignment(int assignmentId, int consultationId) throws ServiceException;

    /**
     * The method removes consultation with given identifier from repository.
     *
     * @param id training identifier in repository.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void delete(int id) throws ServiceException;
}
