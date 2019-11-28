package by.epam.onlinetraining.command.impl.admin.training;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditTrainingCommand implements Command {
    private static final String SHOW_TRAINING_INFO_PAGE = "controller?command=showTrainingInfo&trainingId=";
    private static final String MESSAGE = "&message=";
    private static final String TRAINING_ID = "trainingId";
    private static final String TRAINING_NAME = "name";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PROGRESS = "progress";
    private static final String MENTOR_ID = "mentor";
    private static final String SAVED_TRAINING = "saved";
    private static final String ERROR_MESSAGE = "invalidTraining";
    private static final String TRAINING = "training";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String trainingId = request.getParameter(TRAINING_ID);
        String message = SAVED_TRAINING;
        String name = request.getParameter(TRAINING_NAME);
        String startDate = request.getParameter(START_DATE);
        String endDate = request.getParameter(END_DATE);
        Map<String, String> inputData = new HashMap<>();
        inputData.put(START_DATE, startDate);
        inputData.put(END_DATE, endDate);
        inputData.put(TRAINING_NAME, name);
        Validation validation = new Validation();
        if (!validation.isValidData(inputData)) {
            message = ERROR_MESSAGE;
            return CommandResult.redirect(SHOW_TRAINING_INFO_PAGE + trainingId + MESSAGE + message);
        }
        String progress = request.getParameter(PROGRESS);
        String mentorId = request.getParameter(MENTOR_ID);
        inputData.put(PROGRESS, progress);
        inputData.put(MENTOR_ID, mentorId);
        inputData.put(TRAINING_ID, trainingId);
        TrainingService trainingService = new TrainingService();
        trainingService.update(inputData);
        request.setAttribute(TRAINING, trainingService.findById(Integer.parseInt(trainingId)));
        return CommandResult.redirect(SHOW_TRAINING_INFO_PAGE + trainingId + MESSAGE + message);
    }
}
