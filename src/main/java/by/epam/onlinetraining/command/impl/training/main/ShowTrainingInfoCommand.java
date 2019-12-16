package by.epam.onlinetraining.command.impl.training.main;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class ShowTrainingInfoCommand implements Command {
    private static final String TRAINING_INFO_PAGE = "/WEB-INF/page/training/trainingInfo.jsp";
    private static final String TRAINING_ID = "trainingId";
    private static final String TRAINING = "training";
    private static final String MENTOR_LIST = "mentorList";
    private static final String PROGRESS_SET = "progressSet";
    private static final String MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
        TrainingServiceImpl trainingService = new TrainingServiceImpl();
        Optional<Training> training = trainingService.findById(trainingId);
        EnumSet<TrainingProgress> progressSet = EnumSet.allOf(TrainingProgress.class);

        UserServiceImpl userService = new UserServiceImpl();
        List<User> mentorList = userService.findByRoleAndBlockingStatus(UserRole.MENTOR, BlockingStatus.ACTIVE);

        training.ifPresent(foundedTraining -> {
            request.setAttribute(TRAINING, foundedTraining);
            mentorList.removeIf(mentor -> mentor.getId().equals(foundedTraining.getId()));
            progressSet.remove(foundedTraining.getProgress());
        });
        request.setAttribute(PROGRESS_SET, progressSet);


        request.setAttribute(TRAINING_ID, trainingId);
        request.setAttribute(MENTOR_LIST, mentorList);
        String message = request.getParameter(MESSAGE);//TODO
        if (message != null) {
            request.setAttribute(MESSAGE, message);
        }
        return CommandResult.forward(TRAINING_INFO_PAGE);//TODO
    }
}
