package by.epam.onlinetraining.command.impl.training.main;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTrainingCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String SHOW_TRAININGS = "controller?command=showTrainings&pageNumber=1&limit=5&message=deleted";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.valueOf(request.getParameter(TRAINING_ID));
        TrainingServiceImpl trainingService = new TrainingServiceImpl();
        trainingService.delete(trainingId);
        return CommandResult.redirect(SHOW_TRAININGS);
    }
}
