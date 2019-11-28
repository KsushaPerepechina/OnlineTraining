package by.epam.onlinetraining.command.impl.admin.training;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTrainingCommand implements Command {
    private static final String TRAINING_ID = "trainingId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.valueOf(request.getParameter(TRAINING_ID));
        TrainingService trainingService = new TrainingService();
        trainingService.delete(trainingId);

        ShowTrainingsCommand showTrainingsCommand = new ShowTrainingsCommand();
        return showTrainingsCommand.execute(request, response);
    }
}
