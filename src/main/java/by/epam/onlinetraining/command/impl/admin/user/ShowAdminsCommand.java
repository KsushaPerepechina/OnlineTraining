package by.epam.onlinetraining.command.impl.admin.user;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAdminsCommand implements Command {
    private static final String ADMINS_PAGE = "/WEB-INF/page/mainAdmin/admins.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ShowUsersCommand showUsersCommand = new ShowUsersCommand();
        String result = showUsersCommand.execute(request, UserRole.ADMIN, ADMINS_PAGE);
        return CommandResult.forward(result);
    }
}