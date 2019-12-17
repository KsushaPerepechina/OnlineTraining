package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.ConsultationService;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestConsultationCommand implements Command {
    private static final String ID = "id";
    private static final String TRAINING_ID = "training";
    private static final String SHOW_CONSULTATIONS_COMMAND = "controller?command=showConsultations&pageNumber=1&limit=5&message=";
    private static final String OK_MESSAGE = "added";
    private static final String ERROR_MESSAGE = "invalid";
    private static ConsultationService consultationService = new ConsultationServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int studentId = (Integer) session.getAttribute(ID);
        int trainingId;
        String stringTrainingId = request.getParameter(TRAINING_ID);
        if (stringTrainingId == null) {
            return CommandResult.redirect(SHOW_CONSULTATIONS_COMMAND + ERROR_MESSAGE);
        } else {
            trainingId = Integer.parseInt(stringTrainingId);
        }

        consultationService.requestConsultation(studentId, trainingId);
        return CommandResult.redirect(SHOW_CONSULTATIONS_COMMAND + OK_MESSAGE);
    }
}
