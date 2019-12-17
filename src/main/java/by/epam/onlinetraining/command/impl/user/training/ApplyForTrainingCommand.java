package by.epam.onlinetraining.command.impl.user.training;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.RecordService;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplyForTrainingCommand implements Command {
    private static final String ID = "id";
    private static final String TRAINING_ID = "trainingId";
    private static final String STUDENT_TRAINING_LIST = "studentTrainingList";
    private static final String REDIRECTION_PAGE = "controller?command=showTrainings&message=applicationSent";
    private static RecordService recordService = new RecordServiceImpl();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int studentId = (Integer) session.getAttribute(ID);
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));

        recordService.applyForTraining(studentId, trainingId);

        request.setAttribute(STUDENT_TRAINING_LIST, request.getAttribute(STUDENT_TRAINING_LIST));
        return CommandResult.redirect(REDIRECTION_PAGE);
    }
}
