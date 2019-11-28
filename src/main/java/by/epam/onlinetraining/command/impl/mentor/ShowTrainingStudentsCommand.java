package by.epam.onlinetraining.command.impl.mentor;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.StudentRecord;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TrainingService;
import by.epam.onlinetraining.utils.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

public class ShowTrainingStudentsCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String TRAINING = "training";
    private static final String STUDENT_LIST = "studentList";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String STUDENTS_PAGE = "/WEB-INF/page/mentor/trainingStudents.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        int trainingId = Integer.parseInt(request.getParameter(TRAINING_ID));
        String stringLimit = request.getParameter(LIMIT);
        String stringPageNumber = request.getParameter(PAGE_NUMBER);
        Map<String, String> pageData = new HashMap<>();
        pageData.put(LIMIT, stringLimit);
        pageData.put(PAGE_NUMBER, stringPageNumber);
        Validation validation = new Validation();
        if (!validation.isValidData(pageData)) {
            return CommandResult.forward(ERROR_PAGE);
        }
        int limit = Integer.valueOf(stringLimit);
        int pageNumber = Integer.valueOf(stringPageNumber);
        int offset = limit * (pageNumber - 1);
        TrainingService trainingService = new TrainingService();
        Optional<Training> training = trainingService.findById(trainingId);
        List<StudentRecord> studentList = new LinkedList<>();
        training.ifPresent(tr -> {
            TrainingProgress progress = tr.getProgress();
            request.setAttribute(TRAINING, training.get());
            if (progress == TrainingProgress.REGISTRATION_OPENED) {
                tr.getStudents().forEach(studentRecord -> {
                            StudentStatus status = studentRecord.getStatus();
                            if (status == StudentStatus.REQUESTED || status == StudentStatus.APPROVED
                                    || status == StudentStatus.REJECTED) {
                                studentList.add(studentRecord);
                            }
                        });
            } else {
                tr.getStudents().forEach(studentRecord -> {
                            StudentStatus status = studentRecord.getStatus();
                            if (status == StudentStatus.IN_PROCESS || status == StudentStatus.EXPELLED
                                    || status == StudentStatus.COMPLETED) {
                                studentList.add(studentRecord);
                            }
                        });
            }
        });
        PagesDelimiter<StudentRecord> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(studentList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        int studentListSize = studentList.size();
        request.setAttribute(STUDENT_LIST, studentList.subList(offset, offset + (studentListSize % limit)));//TODO check on IndexOutOfBoundsExc

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return CommandResult.forward(STUDENTS_PAGE);
    }
}
