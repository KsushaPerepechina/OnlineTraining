package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.ConsultationStatus;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.ConsultationRepository;
import by.epam.onlinetraining.repository.impl.RecordRepository;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.assignment.FindByTrainingIdSpecification;
import by.epam.onlinetraining.util.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ConsultationService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String MESSAGE = "There is no consultation with such id.";

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

    public void requestConsultation(int studentId, int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            Consultation consultation = new Consultation(new User(studentId), new Training(trainingId));
            consultationRepository.save(consultation);
            //TODO assignments
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
