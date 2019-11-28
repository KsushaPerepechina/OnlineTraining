package by.epam.onlinetraining.command.impl.common;

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
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class ShowTrainingInfoCommand implements Command {
    private static final String TRAINING_INFO_PAGE = "/WEB-INF/page/common/trainingInfo.jsp";
    private static final String TRAINING_ID = "trainingId";
    private static final String TRAINING = "training";
    private static final String MENTOR_LIST = "mentorList";
    private static final String PROGRESS_SET = "progressSet";
    private static final String MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
        TrainingService trainingService = new TrainingService();
        Optional<Training> training = trainingService.findById(trainingId);
        EnumSet<TrainingProgress> progressSet = EnumSet.allOf(TrainingProgress.class);
        training.ifPresent(foundedTraining -> {
            request.setAttribute(TRAINING, foundedTraining);
            progressSet.remove(foundedTraining.getProgress());
        });
        request.setAttribute(PROGRESS_SET, progressSet);

        UserService userService = new UserService();
        List<User> mentorList = userService.findByRoleAndBlockingStatus(UserRole.MENTOR, BlockingStatus.ACTIVE);
        request.setAttribute(MENTOR_LIST, mentorList);

        String message = request.getParameter(MESSAGE);//TODO
        if (message != null) {
            request.setAttribute(MESSAGE, message);
        }
        return CommandResult.forward(TRAINING_INFO_PAGE);//TODO
    }
}
