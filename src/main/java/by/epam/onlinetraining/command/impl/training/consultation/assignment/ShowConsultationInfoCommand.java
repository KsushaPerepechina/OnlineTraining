package by.epam.onlinetraining.command.impl.training.consultation.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.AssignmentService;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.impl.AssignmentServiceImpl;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;
import by.epam.onlinetraining.util.PagesDelimiter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowConsultationInfoCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String LIMIT = "limit";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String CONSULTATION_ASSIGNMENT_LIST = "consultationAssignmentList";
    private static final String ASSIGNMENT_LIST = "assignmentList";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String CONSULTATION_INFO_PAGE = "/WEB-INF/page/training/consultationInfo.jsp";
    private static AssignmentService assignmentService = new AssignmentServiceImpl();
    private static ConsultationService consultationService = new ConsultationServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        List<Assignment> consultationAssignmentList = assignmentService.findByConsultationId(consultationId);

        int limit = Integer.parseInt(request.getParameter(LIMIT));
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        int offset = limit * (pageNumber - 1);

        PagesDelimiter<Assignment> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(consultationAssignmentList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        List<Assignment> resultConsultationAssignmentList = pagesDelimiter.composePageList(consultationAssignmentList, limit, offset);
        request.setAttribute(CONSULTATION_ASSIGNMENT_LIST, resultConsultationAssignmentList);

        consultationService.findById(consultationId).ifPresent(cons -> {
            try {
                List<Assignment> assignmentList = assignmentService.findByTrainingId(cons.getTraining().getId());
                assignmentList.removeAll(consultationAssignmentList);
                request.setAttribute(ASSIGNMENT_LIST, assignmentList);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });
        request.setAttribute(CONSULTATION_ID, consultationId);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return CommandResult.forward(CONSULTATION_INFO_PAGE);
    }
}
