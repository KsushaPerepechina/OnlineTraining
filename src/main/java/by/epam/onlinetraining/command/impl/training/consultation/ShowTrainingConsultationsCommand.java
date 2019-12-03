package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Assignment;
import by.epam.onlinetraining.entity.Consultation;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationService;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowTrainingConsultationsCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String CONSULTATION_LIST = "consultationList";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String TRAINING_CONSULTATIONS_PAGE = "/WEB-INF/page/training/trainingConsultations.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
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

        ConsultationService consultationService = new ConsultationService();
        List<Consultation> consultationList = consultationService.findByTrainingId(trainingId);
        PagesDelimiter<Consultation> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(consultationList, limit);
        request.setAttribute(TRAINING_ID, trainingId);
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
