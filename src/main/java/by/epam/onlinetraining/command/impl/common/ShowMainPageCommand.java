package by.epam.onlinetraining.command.impl.common;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowMainPageCommand implements Command {
    private static final String MAIN_PAGE = "/WEB-INF/page/common/mainPage.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(MAIN_PAGE);
    }
}
