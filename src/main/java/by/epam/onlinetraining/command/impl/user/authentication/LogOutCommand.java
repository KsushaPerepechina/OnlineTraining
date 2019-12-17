package by.epam.onlinetraining.command.impl.user.authentication;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Designed for user logout.
 */
public class LogOutCommand implements Command {
    private static final String LOG_IN_PAGE = "/WEB-INF/page/user/login.jsp";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ROLE = "role";

    /**
     * Process the request, sing out from profile and generates a result of processing in the form of
     * {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     * @throws ServiceException when {@link by.epam.onlinetraining.exception.RepositoryException} is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute(ID);
        session.removeAttribute(NAME);
        session.removeAttribute(ROLE);
        return CommandResult.forward(LOG_IN_PAGE);
    }
}
