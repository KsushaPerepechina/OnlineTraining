package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.UserRepository;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.user.FindByEmailAndPasswordSpecification;
import by.epam.onlinetraining.specification.impl.user.FindByEmailSpecification;
import by.epam.onlinetraining.specification.impl.user.FindByRoleSpecification;
import by.epam.onlinetraining.utils.RepositoryCreator;
import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PASSWORD = "userPassword";

    public Optional<User> logIn(String email, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailAndPasswordSpecification(email, password));
        } catch (RepositoryException e) {
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
            String password = signUpData.get(PASSWORD);
            String decryptedPassword = DigestUtils.sha512Hex(password);
            User user = new User(firstName, lastName, birthDate, email, decryptedPassword);
            userRepository.save(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByIdSpecification(id));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<User> findByEmail(String email) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailSpecification(email));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findByRole(UserRole role) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleSpecification(role));
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateProfile(int id, String firstName, String lastName) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            User user = new User(id, firstName, lastName);
            userRepository.save(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void updateBalance(int id, BigDecimal balance) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            User user = new User(id, balance);
            userRepository.save(user);
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    public void changeBlockingStatus(int id, boolean blocked) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            User user = new User(id, blocked);
            UserRepository userRepository = repositoryCreator.getUserRepository();
            userRepository.save(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
