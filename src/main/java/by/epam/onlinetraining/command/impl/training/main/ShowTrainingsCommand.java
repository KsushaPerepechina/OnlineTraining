package by.epam.onlinetraining.command.impl.training.main;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingService;
import by.epam.onlinetraining.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowTrainingsCommand implements Command {
    private static final String TRAININGS_PAGE = "/WEB-INF/page/training/trainings.jsp";
    private static final String FINISHED_TRAINING_LIST = "finishedTrainingList";
    private static final String REGISTRATION_OPENED_TRAINING_LIST = "registrationOpenedTrainingList";
    private static final String TRAINING_IN_PROCESS_LIST = "trainingInProcessList";
    private static final String MENTOR_LIST = "mentorList";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        TrainingService trainingService = new TrainingService();
        List<Training> finishedList = trainingService.findByProgress(TrainingProgress.FINISHED);
        request.setAttribute(FINISHED_TRAINING_LIST, finishedList);
        List<Training> inProcessList = trainingService.findByProgress(TrainingProgress.IN_PROCESS);
        request.setAttribute(TRAINING_IN_PROCESS_LIST, inProcessList);
        List<Training> registrationOpenedList = trainingService.findByProgress(TrainingProgress.REGISTRATION_OPENED);
        request.setAttribute(REGISTRATION_OPENED_TRAINING_LIST, registrationOpenedList);

        UserService userService = new UserService();
        List<User> mentorList = userService.findByRoleAndBlockingStatus(UserRole.MENTOR, BlockingStatus.ACTIVE);
        request.setAttribute(MENTOR_LIST, mentorList);

        return CommandResult.forward(TRAININGS_PAGE);
    }
}