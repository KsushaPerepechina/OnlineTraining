package by.epam.onlinetraining.command.impl.user.authentication;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Designed for user logout.
 */
public class LogInCommand implements Command {
    private static final String MAIN_PAGE = "controller?command=showMainPage";
    private static final String LOG_IN_PAGE = "/WEB-INF/page/user/login.jsp";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_PARAMETER = "Wrong login or password.";
    private static final String BLOCKED_ACCOUNT = "Your account is blocked.";
    private static UserService userService = new UserServiceImpl();

    /**
     * Process the request, logs in and generates a result of processing in the form of
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
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Optional<User> optionalUser = userService.logIn(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BlockingStatus blockingStatus = user.getBlockingStatus();
            if (blockingStatus == BlockingStatus.BLOCKED) {
                request.setAttribute(ERROR_MESSAGE, BLOCKED_ACCOUNT);
                return CommandResult.forward(LOG_IN_PAGE);
            }
            Integer id = user.getId();
            UserRole role = user.getRole();
            String name = user.getFirstName();
            session.setAttribute(ID, id);
            session.setAttribute(NAME, name);
            session.setAttribute(ROLE, role);
            return CommandResult.redirect(MAIN_PAGE);
        } else {
            request.setAttribute(ERROR_MESSAGE, WRONG_PARAMETER);
            return CommandResult.forward(LOG_IN_PAGE);
        }
    }
}
