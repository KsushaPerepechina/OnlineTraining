package by.epam.onlinetraining.command.impl.user.profile;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.UserService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class EditProfileCommand implements Command {
    private static final String SHOW_PROFILE = "controller?command=showProfile&userId=";
    private static final String MESSAGE = "&message=";
    private static final String ID = "id";
    private static final String USER_ID = "userId";
    private static final String NAME = "name";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String LANGUAGE = "language";
    private static final String BIRTH_DATE = "birthDate";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String BLOCKING_STATUS = "blockingStatus";
    private static final String ROLE = "role";
    private static final String EDITING_PROFILE = "editedProfile";
    private static final String PROFILE_ERROR = "profileError";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String language = (String)session.getAttribute(LANGUAGE);

        String id = request.getParameter(USER_ID);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String birthDate = request.getParameter(BIRTH_DATE);
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        String blockingStatus = request.getParameter(BLOCKING_STATUS);
        String role = request.getParameter(ROLE);

        Map<String, String> inputData = new HashMap<>();
        inputData.put(FIRST_NAME, firstName);
        inputData.put(LAST_NAME, lastName);
        inputData.put(BIRTH_DATE, birthDate);
        inputData.put(PHONE_NUMBER, phoneNumber);

        Validation validation = new Validation();
        if (!validation.isValidData(inputData)) {
            return CommandResult.redirect(SHOW_PROFILE + id + MESSAGE + PROFILE_ERROR);
        }

        inputData.put(BLOCKING_STATUS, blockingStatus);
        inputData.put(ROLE, role);
        inputData.put(ID, id);
        UserService userService = new UserService();
        userService.updateProfile(inputData, language);
        session.removeAttribute(NAME);
        session.setAttribute(NAME, firstName);
        return CommandResult.redirect(SHOW_PROFILE + id + MESSAGE + EDITING_PROFILE);
    }
}
