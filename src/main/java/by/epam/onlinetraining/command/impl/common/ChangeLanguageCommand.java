package by.epam.onlinetraining.command.impl.common;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Designed to switch language.
 */
public class ChangeLanguageCommand implements Command {
    private static final String MAIN_PAGE = "showMainPage";
    private static final String LANGUAGE = "language";
    private static final String REDIRECT_COMMAND = "controller?command=";
    private static final String LANG = "lang";
    private static final int COMMAND_INDEX = 46;

    /**
     * Process the request, change language and generates a result of processing
     * in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String language = request.getParameter(LANG);
        session.setAttribute(LANGUAGE, language);
        String query = request.getQueryString();
        if (query.length() > COMMAND_INDEX) {
            String page = query.substring(COMMAND_INDEX);
            return CommandResult.redirect(REDIRECT_COMMAND + page);
        } else {
            return CommandResult.redirect(REDIRECT_COMMAND + MAIN_PAGE);
        }
    }
}
