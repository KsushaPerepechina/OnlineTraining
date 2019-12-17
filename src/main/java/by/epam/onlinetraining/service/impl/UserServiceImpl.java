package by.epam.onlinetraining.service.impl;

import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.RepositoryException;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.repository.impl.ConsultationRepository;
import by.epam.onlinetraining.repository.impl.TransactionRepository;
import by.epam.onlinetraining.repository.impl.UserRepository;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.specification.impl.FindByIdSpecification;
import by.epam.onlinetraining.specification.impl.user.*;
import by.epam.onlinetraining.repository.impl.RepositoryCreator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PASSWORD = "userPassword";
    private static final String BLOCKING_STATUS = "blockingStatus";
    private static final String ROLE = "role";
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String UNSUPPORTED_LANG_MESSAGE = "Unsupported language: ";
    private static final String EN_DATE_FORMAT = "MM-dd-yyyy";
    private static final String RU_DATE_FORMAT = "dd.MM.yyyy";
    private static final String SPACE_CHAR = "\u0020";
    private static final String UNDERSCORE_SYMBOL = "\u005f";

    @Override
    public Optional<User> logIn(String email, String password) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailAndPasswordSpecification(email, password));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void signUp(Map<String, String> signUpData, String language) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            String firstName = signUpData.get(FIRST_NAME);
            String lastName = signUpData.get(LAST_NAME);
            LocalDate birthDate;
            String stringBirthDate = signUpData.get(BIRTH_DATE);
            if (EN.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT);
                birthDate = LocalDate.parse(stringBirthDate, formatter);
            } else if (RU.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RU_DATE_FORMAT);
                birthDate = LocalDate.parse(stringBirthDate, formatter);
            } else {
                throw new ServiceException(UNSUPPORTED_LANG_MESSAGE + language);
            }
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

    @Override
    public Optional<User> findById(int id) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByIdSpecification(id, userRepository.getTableName()));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.query(new FindByEmailSpecification(email));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findByRole(UserRole role) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleSpecification(role));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findByRoleAndBlockingStatus(UserRole role, BlockingStatus blockingStatus) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.queryAll(new FindByRoleAndBlockingStatusSpecification(role, blockingStatus));
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void updateProfile(Map<String, String> profileData, String language) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            int id = Integer.parseInt(profileData.get(ID));
            String firstName = profileData.get(FIRST_NAME);
            String lastName = profileData.get(LAST_NAME);
            LocalDate birthDate;
            String stringBirthDate = profileData.get(BIRTH_DATE);
            if (EN.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(EN_DATE_FORMAT);
                birthDate = LocalDate.parse(stringBirthDate, formatter);
            } else if (RU.equals(language)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RU_DATE_FORMAT);
                birthDate = LocalDate.parse(stringBirthDate, formatter);
            } else {
                throw new ServiceException(UNSUPPORTED_LANG_MESSAGE + language);
            }
            String phoneNumber = profileData.get(PHONE_NUMBER);
            String stringBlockingStatus = profileData.get(BLOCKING_STATUS);
            String stringRole = profileData.get(ROLE);
            BlockingStatus blockingStatus = null;
            UserRole role = null;
            if (stringBlockingStatus != null) {
                blockingStatus = BlockingStatus.valueOf(stringBlockingStatus.toUpperCase());
            }
            if (stringRole != null) {
                role = UserRole.valueOf(stringRole.toUpperCase().replace(SPACE_CHAR, UNDERSCORE_SYMBOL));
            }
            User user = new User(id, firstName, lastName, birthDate, null, phoneNumber, blockingStatus,
                    role, null);
            userRepository.save(user);
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void refillBalance(int studentId, BigDecimal sum) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            User user = userRepository.query(new FindByIdSpecification(studentId, userRepository.getTableName())).get();
            user.setBalance(user.getBalance().add(sum));
            Transaction transaction = new Transaction(null, studentId, LocalDate.now(), OperationType.REFILL, sum);
            try {
                repositoryCreator.startTransaction();
                userRepository.save(user);
                transactionRepository.save(transaction);
            } catch (RepositoryException e) {
                LOGGER.error(e.getMessage(), e);
                repositoryCreator.rollbackTransaction();
            }
            repositoryCreator.commitTransaction();
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean payForConsultation(int consultationId) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository userRepository = repositoryCreator.getUserRepository();
            ConsultationRepository consultationRepository = repositoryCreator.getConsultationRepository();
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            Optional<Consultation> consultation = consultationRepository.query(new FindByIdSpecification(consultationId,
                    consultationRepository.getTableName()));
            if (consultation.isPresent()) {
                Consultation cons = consultation.get();
                BigDecimal cost = cons.getCost();
                User student = userRepository.query(new FindByIdSpecification(cons.getStudent().getId(),
                        userRepository.getTableName())).get();
                BigDecimal balance = student.getBalance();
                if (cost.compareTo(balance) > 0) {
                    return false;
                } else {
                    repositoryCreator.startTransaction();
                    student.setBalance(balance.subtract(cost));
                    cons.setPayed(true);
                    Transaction transaction = new Transaction(null, student.getId(), LocalDate.now(),
                            OperationType.PAYMENT, cost);
                    try {
                        userRepository.save(student);
                        consultationRepository.save(cons);
                        transactionRepository.save(transaction);
                    } catch (RepositoryException e) {
                        LOGGER.error(e.getMessage(), e);
                        repositoryCreator.rollbackTransaction();
                    }
                    repositoryCreator.commitTransaction();
                }
            }
        } catch (RepositoryException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return true;
    }
}
