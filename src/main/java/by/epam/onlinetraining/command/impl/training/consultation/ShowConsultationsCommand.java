package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.RecordService;
import by.epam.onlinetraining.service.TrainingService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;
import by.epam.onlinetraining.service.impl.TrainingServiceImpl;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ShowConsultationsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ROLE = "role";
    private static final String ID = "id";
    private static final String TRAINING_ID = "trainingId";
    private static final String CONSULTATION_LIST = "consultationList";
    private static final String TRAINING_LIST = "trainingList";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String TRAINING_CONSULTATIONS_PAGE = "/WEB-INF/page/training/consultations.jsp";
    private static ConsultationService consultationService = new ConsultationServiceImpl();
    private static TrainingService trainingService = new TrainingServiceImpl();
    private static RecordService recordService = new RecordServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(ROLE);
        String stringTrainingId = request.getParameter(TRAINING_ID);
        List<Consultation> consultationList;
        List<Training> studentTrainingList = new ArrayList<>();
        if (UserRole.STUDENT == role) {
            int studentId = (Integer) session.getAttribute(ID);
            consultationList = consultationService.findByStudentId(studentId);
            studentTrainingList = recordService.findStudentTrainingsByStatus(studentId, StudentStatus.IN_PROCESS);
        } else if (UserRole.MENTOR == role && (stringTrainingId == null || stringTrainingId.isEmpty())) {
            int mentorId = (Integer) session.getAttribute(ID);
            List<Consultation> consultations = new LinkedList<>();
            trainingService.findByMentorId(mentorId).forEach(training -> {
                try {
                    consultations.addAll(consultationService.findByTrainingId(training.getId()));
                } catch (ServiceException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
            consultationList = consultations;
        } else {
            int trainingId = Integer.parseInt(stringTrainingId);
            consultationList = consultationService.findByTrainingId(trainingId);
            request.setAttribute(TRAINING_ID, trainingId);
        }

        request.setAttribute(TRAINING_LIST, studentTrainingList);
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

        PagesDelimiter<Consultation> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(consultationList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        List<Consultation> resultConsultationList = pagesDelimiter.composePageList(consultationList, limit, offset);
        request.setAttribute(CONSULTATION_LIST, resultConsultationList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return CommandResult.forward(TRAINING_CONSULTATIONS_PAGE);
    }
}
