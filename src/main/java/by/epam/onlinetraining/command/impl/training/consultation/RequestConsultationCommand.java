package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class RequestConsultationCommand implements Command {
    private static final String ID = "id";
    private static final String TRAINING_ID = "trainingId";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error500.jsp";
    private static final String SHOW_CONSULTATIONS_COMMAND = "controller?command=showConsultations";//TODO
    private static final String PAGE_NUMBER_PARAMETER = "&pageNumber=";
    private static final String LIMIT_PARAMETER = "&limit=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int studentId = (Integer) session.getAttribute(ID);

        String stringTrainingId = request.getParameter(TRAINING_ID);
        String limit = request.getParameter(LIMIT);
        String pageNumber = request.getParameter(PAGE_NUMBER);

        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, limit);
        pageData.put(PAGE_NUMBER, pageNumber);
        pageData.put(TRAINING_ID, stringTrainingId);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int trainingId = Integer.parseInt(stringTrainingId);

        ConsultationService consultationService = new ConsultationService();
        consultationService.requestConsultation(studentId, trainingId);
        //TODO payment

        return CommandResult.redirect(SHOW_CONSULTATIONS_COMMAND + PAGE_NUMBER_PARAMETER + pageNumber
                + LIMIT_PARAMETER + limit);
    }
}
