package by.epam.onlinetraining.command.impl.training.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.AssignmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAssignmentCommand implements Command {
    private static final String ASSIGNMENT_ID = "assignmentId";
    private static final String TRAINING_ID = "trainingId";
    private static final String SHOW_ASSIGNMENTS = "controller?command=showAssignments&pageNumber=1&limit=5&trainingId=";
    private static final String MESSAGE = "&message=deleted";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int assignmentId = Integer.valueOf(request.getParameter(ASSIGNMENT_ID));
        int trainingId = 0;
        String stringTrainingId = request.getParameter(TRAINING_ID);
        if (stringTrainingId != null) {
            trainingId = Integer.parseInt(stringTrainingId);
        }
        AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
        assignmentService.delete(assignmentId);
        return CommandResult.redirect(SHOW_ASSIGNMENTS + trainingId + MESSAGE);
    }
}
