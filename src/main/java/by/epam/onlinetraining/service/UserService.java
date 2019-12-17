package by.epam.onlinetraining.service;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface provides methods for working with objects of the {@link by.epam.onlinetraining.entity.User} type.
 */
public interface UserService {

    /**
     * Method designed for searching user depends on user login and password.
     *
     * @param email is a {@link java.lang.String} object that contains user email
     * @param password is a {@link java.lang.String} object that contains user password
     * @return a {@link java.util.Optional} object with finding {@link by.epam.onlinetraining.entity.User} inside.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    Optional<User> logIn(String email, String password) throws ServiceException;

    /**
     * The method for sign up user with given parameters.
     *
     * @param signUpData a {@link java.util.Map} object that maps user's birthday.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void signUp(Map<String, String> signUpData, String language) throws ServiceException;

    /**
     * The method searches for user with given identifier.
     *
     * @param id an object identifier in database
     * @return a {@link java.util.Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    Optional<User> findById(int id) throws ServiceException;

    /**
     * The method searches for user with given email.
     *
     * @param email an object email in database
     * @return a {@link java.util.Optional} implementation with object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    Optional<User> findByEmail(String email) throws ServiceException;

    /**
     * The method searches for user with given role.
     *
     * @param role a {@link by.epam.onlinetraining.entity.type.UserRole} object identifier in database
     * @return a {@link java.util.List} implementation with {@link User} object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<User> findByRole(UserRole role) throws ServiceException;

    /**
     * The method for update profile information with given parameters.
     *
     * @param role a {@link by.epam.onlinetraining.entity.type.UserRole} object identifier in database
     * @param blockingStatus a {@link by.epam.onlinetraining.entity.type.BlockingStatus} implementation
     *                       with {@link by.epam.onlinetraining.entity.User} object.
     * @return a {@link java.util.List} implementation with {@link User} object.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    List<User> findByRoleAndBlockingStatus(UserRole role, BlockingStatus blockingStatus) throws ServiceException;

    /**
     * The method for update profile information with given parameters.
     *
     * @param profileData a {@link java.util.Map} object, that contains user data.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void updateProfile(Map<String, String> profileData, String language) throws ServiceException;

    /**
     * The method for update balance information with given parameters.
     *
     * @param id a {@link java.lang.Integer} object identifier in database
     * @param balance a {@link java.math.BigDecimal} object that maps sum for refile balance.
     * @throws ServiceException Signals that service exception of some sort has occurred.
     */
    void refillBalance(int id, BigDecimal balance) throws ServiceException;

    boolean payForConsultation(int consultationId) throws ServiceException;
}
