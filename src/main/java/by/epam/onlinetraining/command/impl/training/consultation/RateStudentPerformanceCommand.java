package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RateStudentPerformanceCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String TRAINING_ID = "trainingId";
    private static final String PERFORMANCE = "performance";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error500.jsp";
    private static final String SHOW_TRAINING_CONSULTATIONS_COMMAND = "controller?command=showTrainingConsultations&trainingId=";
    private static final String PAGE_NUMBER_PARAMETER = "&pageNumber=";
    private static final String LIMIT_PARAMETER = "&limit=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String stringConsultationId = request.getParameter(CONSULTATION_ID);
        String stringPerformance = request.getParameter(PERFORMANCE);
        String trainingId = request.getParameter(TRAINING_ID);
        String limit = request.getParameter(LIMIT);
        String pageNumber = request.getParameter(PAGE_NUMBER);

        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, limit);
        pageData.put(PAGE_NUMBER, pageNumber);
        pageData.put(CONSULTATION_ID, stringConsultationId);
        pageData.put(TRAINING_ID, trainingId);
        pageData.put(PERFORMANCE, stringPerformance);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int consultationId = Integer.parseInt(stringConsultationId);
        int performance = Integer.valueOf(stringPerformance);

        ConsultationService consultationService = new ConsultationService();
        consultationService.rateStudentPerformance(consultationId, performance);

        return CommandResult.redirect(SHOW_TRAINING_CONSULTATIONS_COMMAND + trainingId
                + PAGE_NUMBER_PARAMETER + pageNumber + LIMIT_PARAMETER + limit);
    }
}
