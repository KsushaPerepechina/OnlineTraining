package by.epam.onlinetraining.command;

import by.epam.onlinetraining.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Designed to determine the behavior of the implementing class in the form of command
 * to processing the request and form the response.
 */
public interface Command {
    /**
     * Processes the request and forms the answer in the form {@link by.epam.onlinetraining.command.CommandResult}.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return Returns result of processing in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     * @throws ServiceException Throws when {@link by.epam.onlinetraining.exception.RepositoryException} is caught.
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
