package by.epam.onlinetraining.command.impl.training.consultation.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeAssignmentCommand implements Command {
    private static final String ASSIGNMENT = "assignment";
    private static final String CONSULTATION_ID = "consultationId";
    private static final String SHOW_CONSULTATION_INFO_COMMAND =
            "controller?command=showConsultationInfo&pageNumber=1&limit=5&consultationId=";
    private static final String OK_MESSAGE = "&message=added";
    private static final String ERROR_MESSAGE = "&message=invalid";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int assignmentId;
        String stringAssignmentId = request.getParameter(ASSIGNMENT);
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        if (stringAssignmentId == null) {
            return CommandResult.redirect(SHOW_CONSULTATION_INFO_COMMAND + consultationId + ERROR_MESSAGE);
        } else {
            assignmentId = Integer.parseInt(stringAssignmentId);
        }
        ConsultationServiceImpl consultationService = new ConsultationServiceImpl();
        consultationService.includeAssignment(assignmentId, consultationId);
        return CommandResult.redirect(SHOW_CONSULTATION_INFO_COMMAND + consultationId + OK_MESSAGE);
    }
}
