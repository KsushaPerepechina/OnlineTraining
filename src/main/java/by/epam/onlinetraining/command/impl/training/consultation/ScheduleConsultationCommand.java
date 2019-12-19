package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;
import by.epam.onlinetraining.util.DateFormatter;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class ScheduleConsultationCommand implements Command {
    private static final String LANGUAGE = "language";
    private static final String CONSULTATION_ID = "consultationId";
    private static final String TRAINING_ID = "trainingId";
    private static final String DATE = "date";
    private static final String SHOW_CONSULTATIONS = "controller?command=showConsultations&pageNumber=1&limit=5&trainingId=";
    private static final String OK_MESSAGE = "&message=scheduled";
    private static final String ERROR_MESSAGE = "&message=invalid";
    private static ConsultationService consultationService = new ConsultationServiceImpl();
    private static DateFormatter dateFormatter = new DateFormatter();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String language = (String)session.getAttribute(LANGUAGE);
        int consultationId = Integer.parseInt(request.getParameter(CONSULTATION_ID));
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
        String stringDate = request.getParameter(DATE);

        Validation validation = new Validation();
        if (!validation.isValidData(DATE, stringDate)) {
            return CommandResult.redirect(SHOW_CONSULTATIONS + trainingId + ERROR_MESSAGE);
        }

        LocalDate date = dateFormatter.format(stringDate, language);
        consultationService.schedule(consultationId, date);

        return CommandResult.redirect(SHOW_CONSULTATIONS + trainingId + OK_MESSAGE);
    }
}
