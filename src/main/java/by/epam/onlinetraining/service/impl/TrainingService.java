package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.TrainingRepository;
import by.epam.onlinetraining.specification.impl.FindAllSpecification;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.FindAllWithOffsetSpecification;
import by.epam.onlinetraining.specification.impl.training.FindByProgressSpecification;
import by.epam.onlinetraining.specification.impl.training.FindByProgressWithOffsetSpecification;
import by.epam.onlinetraining.utils.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TrainingService { //TODO implements Service<Training> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String NAME = "trainingName";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PROGRESS = "progress";
    private static final String MENTOR_ID = "mentorId";

    public Optional<Training> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.query(new FindByIdSpecification(id, trainingRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //@Override
    public List<Training> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindAllSpecification(trainingRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findAll(Integer limit, Integer offset) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindAllWithOffsetSpecification(trainingRepository.getTableName(),
                    limit, offset));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findByProgress(TrainingProgress progress) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindByProgressSpecification(progress));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findByProgressWithOffset(TrainingProgress progress, int limit, int offset) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindByProgressWithOffsetSpecification(progress, limit, offset));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //@Override
    public void create(Map<String, String> trainingData) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            String name = trainingData.get(NAME);
            Date startDate = Date.valueOf(trainingData.get(START_DATE));
            Date endDate = Date.valueOf(trainingData.get(END_DATE));
            TrainingProgress progress = TrainingProgress.REGISTRATION_OPENED;
            int mentorId = Integer.valueOf(trainingData.get(MENTOR_ID));
            Training training = new Training(null, name, startDate, endDate, progress, new User(mentorId));
            trainingRepository.save(training);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //@Override
    public void update(Map<String, String> trainingData) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            int id = Integer.parseInt(trainingData.get(ID));
            String name = trainingData.get(NAME);
            Date startDate = Date.valueOf(trainingData.get(START_DATE));
            Date endDate = Date.valueOf(trainingData.get(END_DATE));
            TrainingProgress progress = TrainingProgress.valueOf(trainingData.get(PROGRESS));
            int mentorId = Integer.valueOf(trainingData.get(MENTOR_ID));
            Training training = new Training(id, name, startDate, endDate, progress, new User(mentorId));
            trainingRepository.save(training);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            trainingRepository.remove(id);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
