package by.epam.onlinetraining.command.impl.training.student;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ChangeStudentStatusCommand implements Command {
    private static final String RECORD_ID = "recordId";
    private static final String TRAINING_ID = "trainingId";
    private static final String STATUS = "status";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error500.jsp";
    private static final String SHOW_TRAINING_STUDENTS_COMMAND = "controller?command=showTrainingStudents&trainingId=";
    private static final String PAGE_NUMBER_PARAMETER = "&pageNumber=";
    private static final String LIMIT_PARAMETER = "&limit=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String stringRecordId = request.getParameter(RECORD_ID);
        String stringStatus = request.getParameter(STATUS).toUpperCase();
        String trainingId = request.getParameter(TRAINING_ID);
        String limit = request.getParameter(LIMIT);
        String pageNumber = request.getParameter(PAGE_NUMBER);

        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, limit);
        pageData.put(PAGE_NUMBER, pageNumber);
        pageData.put(RECORD_ID, stringRecordId);
        pageData.put(TRAINING_ID, trainingId);
        pageData.put(STATUS, stringStatus);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int recordId = Integer.parseInt(stringRecordId);
        StudentStatus status = StudentStatus.valueOf(stringStatus);

        RecordServiceImpl recordService = new RecordServiceImpl();
        recordService.updateStudentStatus(recordId, status);

        return CommandResult.redirect(SHOW_TRAINING_STUDENTS_COMMAND + trainingId
                + PAGE_NUMBER_PARAMETER + pageNumber + LIMIT_PARAMETER + limit);
    }
}
