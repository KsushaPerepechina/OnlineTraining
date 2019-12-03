package by.epam.onlinetraining.command.impl.common;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowStartPageCommand implements Command {
    private static final String START_PAGE = "/index.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(START_PAGE);
    }
}
