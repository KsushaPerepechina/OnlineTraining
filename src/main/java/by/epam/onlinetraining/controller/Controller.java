package by.epam.onlinetraining.controller;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandFactory;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMAND = "command";
    private static final String ERROR_PAGE = "WEB-INF/pages/error/Error500.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        String parameter = request.getParameter(COMMAND);
        Command command = commandFactory.create(parameter);
        try {
            CommandResult commandResult = command.execute(request, response);//результат выполнения команды
            String page = commandResult.getPage();//страница, на которую перенаправляемся
            if (commandResult.isRedirect()) {
                response.sendRedirect(page);//перенаправление на другую стр
            } else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);//с сохранением запроса переход
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            CommandResult.forward(ERROR_PAGE);//TODO
            response.sendRedirect(ERROR_PAGE);
        }
    }
}
