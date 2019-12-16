package by.epam.onlinetraining.command.impl.training.consultation.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcludeAssignmentCommand implements Command {
    private static final String ASSIGNMENT_ID = "assignmentId";
    private static final String CONSULTATION_ID = "consultationId";
    private static final String SHOW_CONSULTATION_INFO_COMMAND =
            "controller?command=showConsultationInfo&pageNumber=1&limit=5&consultationId=";
    private static final String MESSAGE = "&message=deleted";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int assignmentId = Integer.parseInt(request.getParameter(ASSIGNMENT_ID));
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        ConsultationServiceImpl consultationService = new ConsultationServiceImpl();
        consultationService.excludeAssignment(assignmentId, consultationId);
        return CommandResult.redirect(SHOW_CONSULTATION_INFO_COMMAND + consultationId + MESSAGE);
    }
}
