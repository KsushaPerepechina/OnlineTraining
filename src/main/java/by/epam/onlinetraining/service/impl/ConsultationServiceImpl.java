package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.*;
import by.epam.onlinetraining.entity.type.ConsultationStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.ConsultationAssignmentRepository;
import by.epam.onlinetraining.repository.impl.ConsultationRepository;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.assignment.FindByAssignmentIdAndConsultationIdSpecification;
import by.epam.onlinetraining.specification.impl.record.FindByStudentIdSpecification;
import by.epam.onlinetraining.specification.impl.record.FindByTrainingIdSpecification;
import by.epam.onlinetraining.repository.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ConsultationServiceImpl implements ConsultationService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "There is no consultation with such id.";

    @Override
    public Optional<Consultation> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            return consultationRepository.query(new FindByIdSpecification(id, consultationRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consultation> findByTrainingId(int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            return consultationRepository.queryAll(new FindByTrainingIdSpecification(trainingId,
                    consultationRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consultation> findByStudentId(int studentId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            return consultationRepository.queryAll(new FindByStudentIdSpecification(studentId,
                    consultationRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void rateConsultationQuality(int consultationId, int quality) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            Optional<Consultation> consultation = consultationRepository
                    .query(new FindByIdSpecification(consultationId, consultationRepository.getTableName()));
            consultation.ifPresent(c -> {
                if (c.getPerformance() != 0) {
                    c.setStatus(ConsultationStatus.COMPLETED);
                }
                c.setQuality(quality);
            });
            consultationRepository.save(consultation.orElseThrow(() -> new ServiceException(MESSAGE)));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void rateStudentPerformance(int consultationId, int currentPerformance) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            Optional<Consultation> consultation = consultationRepository
                    .query(new FindByIdSpecification(consultationId, consultationRepository.getTableName()));
            consultation.ifPresent(c -> {
                if (c.getQuality() != 0) {
                    c.setStatus(ConsultationStatus.COMPLETED);
                }
                c.setPerformance(currentPerformance);
            });
            consultationRepository.save(consultation.orElseThrow(() -> new ServiceException(MESSAGE)));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void requestConsultation(int studentId, int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            Consultation consultation = new Consultation(new User(studentId), new Training(trainingId));
            consultationRepository.save(consultation);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void includeAssignment(int assignmentId, int consultationId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationAssignmentRepository assignmentRepository = repositoryCreator.getConsultationAssignmentRepository();
            ConsultationAssignment consultationAssignment = new ConsultationAssignment(new Consultation(consultationId),
                    new Assignment(assignmentId));
            assignmentRepository.save(consultationAssignment);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void excludeAssignment(int assignmentId, int consultationId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationAssignmentRepository assignmentRepository = repositoryCreator.getConsultationAssignmentRepository();
            Optional<ConsultationAssignment> assignment = assignmentRepository
                    .query(new FindByAssignmentIdAndConsultationIdSpecification(assignmentId, consultationId));
            assignment.ifPresent(a -> {
                try {
                    assignmentRepository.remove(a.getId());
                } catch (RepositoryException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            consultationRepository.remove(id);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
