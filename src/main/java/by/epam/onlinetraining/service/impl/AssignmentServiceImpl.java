package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.ConsultationAssignment;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.AssignmentRepository;
import by.epam.onlinetraining.repository.impl.ConsultationAssignmentRepository;
import by.epam.onlinetraining.service.AssignmentService;
import by.epam.onlinetraining.specification.impl.assignment.FindByConsultationIdSpecification;
import by.epam.onlinetraining.specification.impl.record.FindByTrainingIdSpecification;
import by.epam.onlinetraining.repository.impl.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class AssignmentServiceImpl implements AssignmentService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Assignment> findByTrainingId(int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            return assignmentRepository.queryAll(new FindByTrainingIdSpecification(trainingId, assignmentRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Assignment> findByConsultationId(int consultationId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ConsultationAssignmentRepository assignmentRepository = repositoryCreator.getConsultationAssignmentRepository();
            return assignmentRepository.queryAll(new FindByConsultationIdSpecification(consultationId)).stream()
                    .map(ConsultationAssignment::getAssignment)
                    .collect(Collectors.toList());
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Assignment assignment) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            assignmentRepository.save(assignment);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AssignmentRepository assignmentRepository = repositoryCreator.getAssignmentRepository();
            assignmentRepository.remove(id);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
