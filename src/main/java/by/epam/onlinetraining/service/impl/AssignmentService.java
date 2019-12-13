package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.AssignmentRepository;
import by.epam.onlinetraining.repository.impl.TransactionRepository;
import by.epam.onlinetraining.specification.impl.record.FindByTrainingIdSpecification;
import by.epam.onlinetraining.util.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AssignmentService {
    private static final Logger LOGGER = LogManager.getLogger();

    public List<Assignment> findByTrainingId(int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            return assignmentRepository.queryAll(new FindByTrainingIdSpecification(trainingId, assignmentRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void add(Assignment assignment) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            assignmentRepository.save(assignment);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(int assignmentId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            assignmentRepository.remove(assignmentId);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
