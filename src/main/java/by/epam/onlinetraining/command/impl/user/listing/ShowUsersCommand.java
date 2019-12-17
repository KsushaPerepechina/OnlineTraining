package by.epam.onlinetraining.command.impl.user.listing;

import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.UserServiceImpl;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Designed to display a list of users.
 */
public class ShowUsersCommand {
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String USER_LIST = "userList";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static UserService userService = new UserServiceImpl();

    /**
     * Process the request, form user list and returns page for displaying.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param role an {@link by.epam.onlinetraining.entity.type.UserRole} object that contains user role
     * @param page list display page
     * @throws ServiceException when {@link by.epam.onlinetraining.exception.RepositoryException} is caught.
     */
    public String execute(HttpServletRequest request, UserRole role, String page) throws ServiceException {
        String stringLimit = request.getParameter(LIMIT);
        String stringPageNumber = request.getParameter(PAGE_NUMBER);
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

        List<User> userList = userService.findByRole(role);
        PagesDelimiter<User> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(userList, limit);
        List<User> resultUserList = pagesDelimiter.composePageList(userList, limit, offset);
        request.setAttribute(USER_LIST, resultUserList);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(USER_LIST, resultUserList);
        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return page;
    }
}
