package by.epam.onlinetraining.command.impl.admin.user;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;
import by.epam.onlinetraining.utils.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowUsersCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String USER_LIST = "userList";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";

    public String execute(HttpServletRequest request, UserRole role, String page) throws ServiceException {
        PagesDelimiter<User> pagesDelimiter = new PagesDelimiter<>();
        UserService userService = new UserService();
        List<User> fullAdminList = userService.findByRole(role);
        LOGGER.error(role);
        LOGGER.error(fullAdminList);
        String stringLimit = request.getParameter(LIMIT);
        String stringPageNumber = request.getParameter(PAGE_NUMBER);
        LOGGER.error("Limit = " + stringLimit);
        LOGGER.error("Page number = " + stringPageNumber);
        Validation validation = new Validation();
        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, stringLimit);
        pageData.put(PAGE_NUMBER, stringPageNumber);
        if (!validation.isValidData(pageData)) {
            return ERROR_PAGE;
        }
        int limit = Integer.valueOf(stringLimit);
        int pageNumber = Integer.valueOf(stringPageNumber);
        int offset = limit * (pageNumber - 1);
        List<User> userList = userService.findByRole(role, limit, offset);
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(fullAdminList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(USER_LIST, userList);
        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return page;
    }
}
