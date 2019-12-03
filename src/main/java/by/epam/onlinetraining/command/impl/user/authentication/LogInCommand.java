package by.epam.onlinetraining.command.impl.user.authentication;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LogInCommand implements Command {
    private static final String MAIN_PAGE = "controller?command=showMainPage";
    private static final String LOG_IN_PAGE = "/WEB-INF/page/common/login.jsp";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String WRONG_PARAMETER = "Wrong login or password.";
    private static final String BLOCKED_ACCOUNT = "Your account is blocked.";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserService service = new UserService();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Optional<User> optionalUser = service.logIn(login, password);
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
