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

public class RateConsultationQualityCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String QUALITY = "quality";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error500.jsp";
    private static final String SHOW_STUDENT_CONSULTATIONS_COMMAND = "controller?command=showConsultations";
    private static final String PAGE_NUMBER_PARAMETER = "&pageNumber=";
    private static final String LIMIT_PARAMETER = "&limit=";
    private static ConsultationService consultationService = new ConsultationServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String stringConsultationId = request.getParameter(CONSULTATION_ID);
        String stringQuality = request.getParameter(QUALITY);
        String limit = request.getParameter(LIMIT);
        String pageNumber = request.getParameter(PAGE_NUMBER);

        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, limit);
        pageData.put(PAGE_NUMBER, pageNumber);
        pageData.put(CONSULTATION_ID, stringConsultationId);
        pageData.put(QUALITY, stringQuality);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int consultationId = Integer.parseInt(stringConsultationId);
        int quality = Integer.valueOf(stringQuality);

        consultationService.rateConsultationQuality(consultationId, quality);

        return CommandResult.redirect(SHOW_STUDENT_CONSULTATIONS_COMMAND + PAGE_NUMBER_PARAMETER + pageNumber
                + LIMIT_PARAMETER + limit);
    }
}
