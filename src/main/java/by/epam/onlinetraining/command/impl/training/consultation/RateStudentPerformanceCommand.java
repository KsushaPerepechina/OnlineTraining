package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;
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
    private static final String SHOW_TRAINING_CONSULTATIONS_COMMAND = "controller?command=showConsultations&trainingId=";
    private static final String PAGE_NUMBER_PARAMETER = "&pageNumber=";
    private static final String LIMIT_PARAMETER = "&limit=";
    private static final String OK_MESSAGE = "&message=rated";
    private static final String ERROR_MESSAGE = "&message=invalid";
    private static ConsultationService consultationService = new ConsultationServiceImpl();

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
            return CommandResult.redirect(SHOW_TRAINING_CONSULTATIONS_COMMAND + trainingId
                    + PAGE_NUMBER_PARAMETER + pageNumber + LIMIT_PARAMETER + limit + ERROR_MESSAGE);
        }
        int consultationId = Integer.parseInt(stringConsultationId);
        int performance = Integer.valueOf(stringPerformance);

        consultationService.rateStudentPerformance(consultationId, performance);
        return CommandResult.redirect(SHOW_TRAINING_CONSULTATIONS_COMMAND + trainingId
                + PAGE_NUMBER_PARAMETER + pageNumber + LIMIT_PARAMETER + limit + OK_MESSAGE);
    }
}
