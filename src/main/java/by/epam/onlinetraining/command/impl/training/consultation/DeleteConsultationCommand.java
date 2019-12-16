package by.epam.onlinetraining.command.impl.training.consultation;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.ConsultationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteConsultationCommand implements Command {
    private static final String CONSULTATION_ID = "consultationId";
    private static final String TRAINING_ID = "trainingId";
    private static final String SHOW_CONSULTATIONS = "controller?command=showConsultations&pageNumber=1&limit=5&trainingId=";
    private static final String MESSAGE = "&message=deleted";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int consultationId = Integer.valueOf(request.getParameter(CONSULTATION_ID));
        int trainingId = 0;
        String stringTrainingId = request.getParameter(TRAINING_ID);
        if (stringTrainingId != null) {
            trainingId = Integer.parseInt(stringTrainingId);
        }
        ConsultationServiceImpl consultationService = new ConsultationServiceImpl();
        consultationService.delete(consultationId);
        return CommandResult.redirect(SHOW_CONSULTATIONS + trainingId + MESSAGE);
    }
}
