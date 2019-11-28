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
    private static final String ERROR_500_PAGE = "WEB-INF/page/error/Error500.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();//создание фабрики команды
        String parameter = request.getParameter(COMMAND);//получение команды из параметров запроса
        Command command = commandFactory.create(parameter);//создание команды
        try {
            CommandResult commandResult = command.execute(request, response);//вызов выполнения команды и получение результата выполнения команды(страница и действие)
            String page = commandResult.getPage();//страница, на которую перенаправляемся
            if (commandResult.isRedirect()) {
                response.sendRedirect(page);//перенаправление на другую стр
            } else {
                ServletContext servletContext = getServletContext();//контекст сервлета
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);//диспетчер запроса
                requestDispatcher.forward(request, response);//с сохранением запроса переход
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            //CommandResult.forward(ERROR_500_PAGE);//TODO
            response.sendRedirect(ERROR_500_PAGE);
        }
    }
}
