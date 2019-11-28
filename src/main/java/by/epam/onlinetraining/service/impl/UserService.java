package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.UserRepository;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.user.*;
import by.epam.onlinetraining.utils.RepositoryCreator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PASSWORD = "userPassword";

    public Optional<User> logIn(String email, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailAndPasswordSpecification(email, password));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void signUp(Map<String, String> signUpData) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {//TODO try-with-resources
            UserRepository userRepository = repositoryCreator.getUserRepository();
            String firstName = signUpData.get(FIRST_NAME);
            String lastName = signUpData.get(LAST_NAME);
            Date birthDate = Date.valueOf(signUpData.get(BIRTH_DATE));
            String email = signUpData.get(EMAIL);
            String phoneNumber = signUpData.get(PHONE_NUMBER);
            String password = signUpData.get(PASSWORD);
            String decryptedPassword = DigestUtils.sha512Hex(password);
            BigDecimal balance = new BigDecimal(0);
            User user = new User(firstName, lastName, birthDate, email, phoneNumber, decryptedPassword, balance);
            userRepository.save(user);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByIdSpecification(id, userRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findByEmail(String email) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailSpecification(email));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findByRole(UserRole role) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleSpecification(role));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findByRole(UserRole role, int limit, int offset) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleWithOffsetSpecification(role, limit, offset));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findByRoleAndBlockingStatus(UserRole role, BlockingStatus blockingStatus) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleAndBlockingStatusSpecification(role, blockingStatus));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateProfile(Map<String, String> profileData) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            int id = Integer.parseInt(profileData.get(ID));
            String firstName = profileData.get(FIRST_NAME);
            String lastName = profileData.get(LAST_NAME);
            Date birthDate = Date.valueOf(profileData.get(BIRTH_DATE));
            String phoneNumber = profileData.get(PHONE_NUMBER);
            User user = new User(id, firstName, lastName, birthDate, phoneNumber);
            userRepository.save(user);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateBalance(int id, BigDecimal balance) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            User user = new User(id, balance);
            userRepository.save(user);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void changeBlockingStatus(int id, BlockingStatus blockingStatus) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            User user = new User(id, blockingStatus);
            UserRepository userRepository = repositoryCreator.getUserRepository();
            userRepository.save(user);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
