package by.epam.onlinetraining.command.impl.user.listing;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Designed to display a list of students.
 */
public class ShowStudentsCommand implements Command {
    private static final String STUDENTS_PAGE = "/WEB-INF/page/user/listing/students.jsp";

    /**
     * Process the request, form student list and generates a result of processing in the form of
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
        ShowUsersCommand showUsersCommand = new ShowUsersCommand();
        String result = showUsersCommand.execute(request, UserRole.STUDENT, STUDENTS_PAGE);
        return CommandResult.forward(result);
    }
}
