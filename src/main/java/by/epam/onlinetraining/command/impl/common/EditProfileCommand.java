package by.epam.onlinetraining.command.impl.common;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class EditProfileCommand implements Command {
    private static final String PROFILE = "controller?command=showProfile";
    private static final String MESSAGE = "&message=";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EDITING_PROFILE = "editedProfile";
    private static final String PROFILE_ERROR = "profileError";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(ID);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Date birthDate = Date.valueOf(request.getParameter(BIRTH_DATE));
        String phoneNumber = request.getParameter(PHONE_NUMBER);

        Map<String, String> inputData = new HashMap<>();
        inputData.put(FIRST_NAME, firstName);
        inputData.put(LAST_NAME, lastName);
        inputData.put(BIRTH_DATE, birthDate.toString());
        inputData.put(PHONE_NUMBER, phoneNumber);

        Validation validation = new Validation();
        if (!validation.isValidData(inputData)) {
            return CommandResult.redirect(PROFILE + MESSAGE + PROFILE_ERROR);
        }

        inputData.put(ID, id.toString());
        UserService userService = new UserService();
        userService.updateProfile(inputData);
        session.removeAttribute(NAME);
        session.setAttribute(NAME, firstName);
        return CommandResult.redirect(PROFILE + MESSAGE + EDITING_PROFILE);
    }
}
