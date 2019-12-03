package by.epam.onlinetraining.command.impl.user.listing;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowStudentsCommand implements Command {
    private static final String STUDENTS_PAGE = "/WEB-INF/page/user/students.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ShowUsersCommand showUsersCommand = new ShowUsersCommand();
        String result = showUsersCommand.execute(request, UserRole.STUDENT, STUDENTS_PAGE);
        return CommandResult.forward(result);
    }
}
