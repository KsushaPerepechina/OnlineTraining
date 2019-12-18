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

/**
 * Provides a HTTP servlet class suitable for a Web site.
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMAND = "command";
    private static final String ERROR_500_PAGE = "WEB-INF/page/error/Error500.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes the request by obtaining a command from the {@link javax.servlet.http.HttpServletRequest} object,
     * execute this command and redirects or forwards on destination page depending on the result of the command execution.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @throws ServletException The general exception that a servlet may throw when some problems occur.
     * @throws IOException signals that an I/O exception of some type has occurred.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        String parameter = request.getParameter(COMMAND);
        Command command = commandFactory.create(parameter);
        try {
            CommandResult commandResult = command.execute(request, response);
            String page = commandResult.getPage();
            if (commandResult.isRedirect()) {
                response.sendRedirect(page);
            } else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            response.sendRedirect(ERROR_500_PAGE);
        }
    }
}
