package by.epam.onlinetraining.command.admin;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminStartPageCommand implements Command {

    private static final String ADMIN_ORDERS = "/WEB-INF/pages/admin/roomSelection.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return CommandResult.forward(ADMIN_ORDERS);
    }
}
