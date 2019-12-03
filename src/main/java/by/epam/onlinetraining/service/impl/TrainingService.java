package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.TrainingRepository;
import by.epam.onlinetraining.specification.impl.FindAllSpecification;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.training.FindByProgressSpecification;
import by.epam.onlinetraining.util.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String UNSUPPORTED_LANG_MESSAGE = "Unsupported language: ";
    private static final String EN_DATE_FORMAT = "MM-dd-yyyy";
    private static final String RU_DATE_FORMAT = "dd.MM.yyyy";

    public Optional<Training> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.query(new FindByIdSpecification(id, trainingRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findAll() throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindAllSpecification(trainingRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findByProgress(TrainingProgress progress) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            return trainingRepository.queryAll(new FindByProgressSpecification(progress, trainingRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void update(Map<String, String> trainingData, String language) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            TrainingRepository trainingRepository = repositoryCreator.getTrainingRepository();
            String stringId = (trainingData.get(ID));
            Integer id = null;
            if (stringId != null) {
                id = Integer.parseInt(stringId);
            }
            String name = trainingData.get(NAME);
            LocalDate startDate;
            LocalDate endDate;
            String stringStartDate = trainingData.get(START_DATE);
            String stringEndDate = trainingData.get(END_DATE);
            if (EN.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT);
                startDate = LocalDate.parse(stringStartDate, formatter);
                endDate = LocalDate.parse(stringEndDate, formatter);
            } else if (RU.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RU_DATE_FORMAT);
                startDate = LocalDate.parse(stringStartDate, formatter);
                endDate = LocalDate.parse(stringEndDate, formatter);
            } else {
                throw new ServiceException(UNSUPPORTED_LANG_MESSAGE + language);
            }
            String stringProgress = trainingData.get(PROGRESS);
            TrainingProgress progress = null;
            if (stringProgress != null) {
                progress = TrainingProgress.valueOf(trainingData.get(PROGRESS));
            }
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
