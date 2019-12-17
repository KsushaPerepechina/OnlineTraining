package by.epam.onlinetraining.command.impl.user.authentication;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.UserServiceImpl;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Designed for user sign up.
 */
public class SignUpCommand implements Command {
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String LANGUAGE = "language";
    private static final String BIRTH_DATE = "birthDate";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "userPassword";
    private static final String PASSWORD_DUPLICATION = "userPasswordDuplication";
    private static final String START_PAGE = "controller?command=startLogIn";
    private static final String LOG_IN_PAGE = "/WEB-INF/page/user/login.jsp";
    private static final String SIGN_UP_ERROR = "signUpError";
    private static final String LOGIN_ERROR = "loginError";
    private static UserService userService = new UserServiceImpl();

    /**
     * Process the request, sing up user and generates a result of processing in the form of
     * {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     * @throws ServiceException when {@link by.epam.onlinetraining.exception.RepositoryException} is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        HttpSession session = request.getSession();
        String language = (String)session.getAttribute(LANGUAGE);

        String birthDate = request.getParameter(BIRTH_DATE);
        String email = request.getParameter(EMAIL);
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        String password = request.getParameter(PASSWORD);
        String passwordDuplication = request.getParameter(PASSWORD_DUPLICATION);

        Map<String, String> signUpData = new HashMap<>();
        signUpData.put(FIRST_NAME, firstName);
        signUpData.put(LAST_NAME, lastName);
        signUpData.put(BIRTH_DATE, birthDate);
        signUpData.put(EMAIL, email);
        signUpData.put(PHONE_NUMBER, phoneNumber);
        signUpData.put(PASSWORD, password);

        Validation validation = new Validation();
        if (!validation.isValidData(signUpData)) {
            String errorName = validation.getInvalidData();
            request.setAttribute(SIGN_UP_ERROR, errorName);
            return CommandResult.forward(LOG_IN_PAGE);
        }

        if (!password.equals(passwordDuplication)) {
            request.setAttribute(SIGN_UP_ERROR, PASSWORD_DUPLICATION);
            return CommandResult.forward(LOG_IN_PAGE);
        }

        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            request.setAttribute(LOGIN_ERROR, LOGIN);
            return CommandResult.forward(LOG_IN_PAGE);
        }
        userService.signUp(signUpData, language);
        return CommandResult.redirect(START_PAGE);
    }
}
