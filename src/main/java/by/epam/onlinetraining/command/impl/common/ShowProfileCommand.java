package by.epam.onlinetraining.command.impl.common;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ShowProfileCommand implements Command {
    private static final String PROFILE = "/WEB-INF/page/common/profile.jsp";
    private static final String ID = "id";
    private static final String USER = "user";
    private static final String MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();//извлечение сессии
        Integer id = (Integer) session.getAttribute(ID);//взятие id
        UserService userService = new UserService();
        Optional<User> user = userService.findById(id);//поиск по id
        user.ifPresent(usr -> request.setAttribute(USER, usr));
        String message = request.getParameter(MESSAGE);//TODO
        if (message != null) {
            request.setAttribute(MESSAGE, message);
        }
        return CommandResult.forward(PROFILE);//TODO
    }
}
