package by.epam.onlinetraining.command.impl.user.profile;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.EnumSet;
import java.util.Optional;

/**
 * Designed to display user's profile information.
 */
public class ShowProfileCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String ID = "id";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ROLE_SET = "roleSet";
    private static final String PROFILE = "/WEB-INF/page/user/profile.jsp";
    private static final String MESSAGE = "message";

    /**
     * Process the request, display user's profile information and generates a result of processing in the form of
     * {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     * @throws ServiceException when {@link by.epam.onlinetraining.exception.RepositoryException} is caught.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String stringId = request.getParameter(USER_ID);
        Integer id;
        HttpSession session = request.getSession();
        if (stringId == null) {
            id = (Integer)session.getAttribute(ID);
        } else {
            id = Integer.parseInt(stringId);
        }
        EnumSet<UserRole> roleSet = EnumSet.of(UserRole.MENTOR, UserRole.STUDENT, UserRole.ADMIN);

        UserServiceImpl userService = new UserServiceImpl();
        Optional<User> user = userService.findById(id);
        user.ifPresent(usr -> {
            request.setAttribute(USER, usr);
            UserRole role = usr.getRole();
            roleSet.remove(role);
            UserRole currentUserRole = (UserRole) session.getAttribute(ROLE);
            if (UserRole.MAIN_ADMIN != currentUserRole) {
                roleSet.remove(UserRole.ADMIN);
            }
        });
        request.setAttribute(ROLE_SET, roleSet);
        String message = request.getParameter(MESSAGE);
        if (message != null) {
            request.setAttribute(MESSAGE, message);
        }
        return CommandResult.forward(PROFILE);
    }
}
