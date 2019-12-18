package by.epam.onlinetraining.command.impl.training.main;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.BlockingStatus;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.entity.type.UserRole;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.RecordService;
import by.epam.onlinetraining.service.TrainingService;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;
import by.epam.onlinetraining.service.impl.TrainingServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class ShowTrainingsCommand implements Command {
    private static final String ROLE = "role";
    private static final String ID = "id";
    private static final String FINISHED_TRAINING_LIST = "finishedTrainingList";
    private static final String REGISTRATION_OPENED_TRAINING_LIST = "registrationOpenedTrainingList";
    private static final String TRAINING_IN_PROCESS_LIST = "trainingInProcessList";
    private static final String MENTOR_LIST = "mentorList";
    private static final String STUDENT_TRAINING_LIST = "studentTrainingList";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String TRAININGS_PAGE = "/WEB-INF/page/training/trainings.jsp";
    private static TrainingService trainingService = new TrainingServiceImpl();
    private static UserService userService = new UserServiceImpl();
    private static RecordService recordService = new RecordServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(ROLE);

        List<Training> finishedList;
        List<Training> inProcessList;
        List<Training> registrationOpenedList;

        if (UserRole.MENTOR == role) {
            int mentorId = (Integer) session.getAttribute(ID);
            finishedList = trainingService.findByProgressAndMentorId(TrainingProgress.FINISHED, mentorId);
            inProcessList = trainingService.findByProgressAndMentorId(TrainingProgress.IN_PROCESS, mentorId);
            registrationOpenedList = trainingService
                    .findByProgressAndMentorId(TrainingProgress.REGISTRATION_OPENED, mentorId);
        } else {
            finishedList = trainingService.findByProgress(TrainingProgress.FINISHED);
            inProcessList = trainingService.findByProgress(TrainingProgress.IN_PROCESS);
            registrationOpenedList = trainingService.findByProgress(TrainingProgress.REGISTRATION_OPENED);
        }

        List<User> mentorList = userService.findByRoleAndBlockingStatus(UserRole.MENTOR, BlockingStatus.ACTIVE);
        request.setAttribute(MENTOR_LIST, mentorList);
        request.setAttribute(FINISHED_TRAINING_LIST, finishedList);
        request.setAttribute(TRAINING_IN_PROCESS_LIST, inProcessList);
        request.setAttribute(REGISTRATION_OPENED_TRAINING_LIST, registrationOpenedList);
        List<Training> studentTrainingList = new ArrayList<>();
        if (UserRole.STUDENT == role) {
            int studentId = (Integer) session.getAttribute(ID);
            studentTrainingList = recordService.findStudentTrainings(studentId);

        }
        request.setAttribute(STUDENT_TRAINING_LIST, studentTrainingList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }

        return CommandResult.forward(TRAININGS_PAGE);
    }
}