package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.RecordRepository;
import by.epam.onlinetraining.service.RecordService;
import by.epam.onlinetraining.specification.impl.record.FindByStudentIdSpecification;
import by.epam.onlinetraining.specification.impl.record.FindByTrainingIdSpecification;
import by.epam.onlinetraining.repository.RepositoryCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class RecordServiceImpl implements RecordService {
    private static final Logger LOGGER = LogManager.getLogger();

    public void updateStudentStatus(int recordId, StudentStatus status) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RecordRepository recordRepository = repositoryCreator.getRecordRepository();
            Record record = new Record(recordId, null, null, status, null);
            recordRepository.save(record);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Record> findByTrainingId(int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RecordRepository recordRepository = repositoryCreator.getRecordRepository();
            return recordRepository.queryAll(new FindByTrainingIdSpecification(trainingId, recordRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Record> findByStudentId(int studentId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RecordRepository recordRepository = repositoryCreator.getRecordRepository();
            return recordRepository.queryAll(new FindByStudentIdSpecification(studentId, recordRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Training> findStudentTrainings(int studentId) throws ServiceException {
        List<Training> studentTrainings = new LinkedList<>();
        findByStudentId(studentId).forEach(record -> studentTrainings.add(record.getTraining()));
        return studentTrainings;
    }

    public List<Training> findStudentTrainingsByStatus(int studentId, StudentStatus status) throws ServiceException {
        List<Training> studentTrainings = new LinkedList<>();
        findByStudentId(studentId).forEach(record -> {
            if (status == record.getStatus()) {
                studentTrainings.add(record.getTraining());
            }
        });
        return studentTrainings;
    }

    public void rateStudent(int recordId, int mark) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RecordRepository recordRepository = repositoryCreator.getRecordRepository();
            StudentStatus status = StudentStatus.COMPLETED;
            Record record = new Record(recordId, null, null, status, mark);
            recordRepository.save(record);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void applyForTraining(int studentId, int trainingId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            RecordRepository recordRepository = repositoryCreator.getRecordRepository();
            Record record = new Record(null, new User(studentId), new Training(trainingId),
                    StudentStatus.REQUESTED, null);
            recordRepository.save(record);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}