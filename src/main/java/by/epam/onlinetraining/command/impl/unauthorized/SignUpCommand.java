package by.epam.onlinetraining.command.impl.unauthorized;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;
import by.epam.onlinetraining.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String BIRTH_DATE = "birthDate";
    private static final String LOGIN = "login";//TODO
    private static final String PASSWORD = "userPassword";
    private static final String START_PAGE = "controller?command=startLogIn";//TODO
    private static final String LOG_IN_PAGE = "/WEB-INF/page/common/login.jsp";
    private static final String SIGN_UP_ERROR = "signUpError";
    private static final String LOGIN_ERROR = "loginError";//emailError

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String birthDate = request.getParameter(BIRTH_DATE);
        String email = request.getParameter(EMAIL);
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        String password = request.getParameter(PASSWORD);

        Map<String, String> signUpData = new HashMap<>();
        signUpData.put(FIRST_NAME, firstName);
        signUpData.put(LAST_NAME, lastName);
        signUpData.put(BIRTH_DATE, birthDate);
        signUpData.put(EMAIL, email);
        signUpData.put(PHONE_NUMBER, phoneNumber);
        signUpData.put(PASSWORD, password);

        LOGGER.error(firstName + lastName + birthDate, email, phoneNumber, password);

        Validation validation = new Validation();
        if (!validation.isValidData(signUpData)) {
            String errorName = validation.getInvalidData();
            request.setAttribute(SIGN_UP_ERROR, errorName);
            return CommandResult.forward(LOG_IN_PAGE);
        }
        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isPresent()) {
            request.setAttribute(LOGIN_ERROR, LOGIN);
            return CommandResult.forward(LOG_IN_PAGE);
        }
        userService.signUp(signUpData);
        return CommandResult.redirect(START_PAGE);
    }
}
