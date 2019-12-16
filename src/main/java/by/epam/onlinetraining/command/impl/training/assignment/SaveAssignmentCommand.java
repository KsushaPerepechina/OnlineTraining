package by.epam.onlinetraining.command.impl.training.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.AssignmentType;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.AssignmentServiceImpl;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class SaveAssignmentCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String ASSIGNMENT_NAME = "assignmentName";
    private static final String TYPE = "type";
    private static final String OK_MESSAGE = "added";
    private static final String ERROR_MESSAGE = "invalid";
    private static final String SHOW_ASSIGNMENTS = "controller?command=showAssignments&pageNumber=1&limit=5&trainingId=";
    private static final String MESSAGE = "&message=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
        String name = request.getParameter(ASSIGNMENT_NAME);
        String stringType = request.getParameter(TYPE);

        Map<String, String> inputData = new HashMap<>();
        inputData.put(ASSIGNMENT_NAME, name);
        inputData.put(TYPE, stringType);
        Validation validation = new Validation();
        if (!validation.isValidData(inputData)) {
            return CommandResult.redirect(SHOW_ASSIGNMENTS + trainingId + MESSAGE + ERROR_MESSAGE);
        }

        AssignmentType type = AssignmentType.valueOf(stringType.toUpperCase());
        Assignment assignment = new Assignment(null, name, type, new Training(trainingId));
        AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
        assignmentService.add(assignment);

        return CommandResult.redirect(SHOW_ASSIGNMENTS + trainingId + MESSAGE + OK_MESSAGE);
    }
}
