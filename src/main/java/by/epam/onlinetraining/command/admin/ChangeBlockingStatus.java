package by.epam.onlinetraining.command.admin;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeBlockingStatus implements Command {

    private static final String ID_CLIENT = "idClient";
    private static final String BLOCKING_STATUS = "blockingStatus";
    private static final String USER_COMMAND = "by/epam/onlinetraining/controller";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Integer id_client = Integer.valueOf(request.getParameter(ID_CLIENT));
        boolean blocked = Boolean.parseBoolean(request.getParameter(BLOCKING_STATUS));

        UserService userService = new UserService();
        userService.changeBlockingStatus(id_client, blocked);

        return CommandResult.redirect(USER_COMMAND);
    }
}
