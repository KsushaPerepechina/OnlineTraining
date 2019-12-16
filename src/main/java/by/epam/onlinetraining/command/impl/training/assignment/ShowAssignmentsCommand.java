package by.epam.onlinetraining.command.impl.training.assignment;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.AssignmentServiceImpl;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ShowAssignmentsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ROLE = "role";
    private static final String ID = "id";
    private static final String TRAINING_ID = "trainingId";
    private static final String ASSIGNMENT_LIST = "assignmentList";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String TRAINING_ASSIGNMENTS_PAGE = "/WEB-INF/page/training/assignments.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(ROLE);
        List<Assignment> assignmentList;
        AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
        if (UserRole.STUDENT == role) {
            int studentId = (Integer) session.getAttribute(ID);
            RecordServiceImpl recordService = new RecordServiceImpl();
            List<Training> trainingList = recordService.findStudentTrainings(studentId);
            List<Assignment> assignments = new ArrayList<>();
            trainingList.forEach(training -> {
                try {
                    assignments.addAll(assignmentService.findByTrainingId(training.getId()));
                } catch (ServiceException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
            assignmentList = assignments;
        } else {
            int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
            assignmentList = assignmentService.findByTrainingId(trainingId);
            request.setAttribute(TRAINING_ID, trainingId);
        }

        String stringLimit = request.getParameter(LIMIT);
        String stringPageNumber = request.getParameter(PAGE_NUMBER);
        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, stringLimit);
        pageData.put(PAGE_NUMBER, stringPageNumber);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int limit = Integer.valueOf(stringLimit);
        int pageNumber = Integer.valueOf(stringPageNumber);
        int offset = limit * (pageNumber - 1);

        PagesDelimiter<Assignment> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(assignmentList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        List<Assignment> resultAssignmentList = pagesDelimiter.composePageList(assignmentList, limit, offset);
        request.setAttribute(ASSIGNMENT_LIST, resultAssignmentList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return CommandResult.forward(TRAINING_ASSIGNMENTS_PAGE);
    }
}
