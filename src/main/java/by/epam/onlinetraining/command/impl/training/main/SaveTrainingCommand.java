package by.epam.onlinetraining.command.impl.training.main;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveTrainingCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String TRAINING_NAME = "trainingName";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String LANGUAGE = "language";
    private static final String PROGRESS = "progress";
    private static final String MENTOR_ID = "mentorId";
    private static final String TRAININGS_ADDRESS = "controller?command=showTrainings";
    private static final String SHOW_TRAINING_INFO_ADDRESS = "controller?command=showTrainingInfo&trainingId=";
    private static final String MESSAGE = "&message=";
    private static final String ERROR_MESSAGE = "invalid";
    private static final String ADDED_TRAINING = "added";
    private static final String EDITED_TRAINING = "edited";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String language = (String)session.getAttribute(LANGUAGE);
        String redirectionPage = TRAININGS_ADDRESS;
        String message = ADDED_TRAINING;
        String trainingId = request.getParameter(TRAINING_ID);
        Map<String, String> pageData = new HashMap<>();
        if (trainingId != null) {
            message = EDITED_TRAINING;
            redirectionPage = SHOW_TRAINING_INFO_ADDRESS + trainingId;
            pageData.put(TRAINING_ID, trainingId);
            String progress = request.getParameter(PROGRESS);
            pageData.put(PROGRESS, progress);
        }
        String name = request.getParameter(TRAINING_NAME);
        String startDate = request.getParameter(START_DATE);
        String endDate = request.getParameter(END_DATE);
        String mentorId = request.getParameter(MENTOR_ID);
        pageData.put(TRAINING_NAME, name);
        pageData.put(START_DATE, startDate);
        pageData.put(END_DATE, endDate);
        pageData.put(MENTOR_ID, mentorId);

        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            message = ERROR_MESSAGE;
            return CommandResult.redirect(redirectionPage + MESSAGE + message);
        }

        TrainingService trainingService = new TrainingService();
        trainingService.update(pageData, language);
        return CommandResult.redirect(redirectionPage + MESSAGE + message);
    }
}
