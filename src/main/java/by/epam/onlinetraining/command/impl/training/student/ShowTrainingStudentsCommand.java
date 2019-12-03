package by.epam.onlinetraining.command.impl.training.student;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.entity.Training;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.StudentStatus;
import by.epam.onlinetraining.entity.type.TrainingProgress;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.RecordService;
import by.epam.onlinetraining.service.impl.TrainingService;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ShowTrainingStudentsCommand implements Command {
    private static final String TRAINING_ID = "trainingId";
    private static final String PROGRESS = "progress";
    private static final String STUDENT_LIST = "recordList";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String LIMIT = "limit";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String MESSAGE = "message";
    private static final String NOTIFY_MESSAGE = "notifyMessage";
    private static final String STUDENTS_PAGE = "/WEB-INF/page/training/trainingStudents.jsp";

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

        RecordService recordService = new RecordService();
        List<Record> records = recordService.findAllByTrainingId(trainingId);

        TrainingService trainingService = new TrainingService();
        Optional<Training> training = trainingService.findById(trainingId);
        List<Record> recordList = new LinkedList<>();
        training.ifPresent(tr -> {
            TrainingProgress progress = tr.getProgress();
            request.setAttribute(PROGRESS, progress);
            if (progress == TrainingProgress.REGISTRATION_OPENED) {
                records.forEach(record -> {
                            StudentStatus status = record.getStatus();
                            if (status == StudentStatus.REQUESTED || status == StudentStatus.APPROVED
                                    || status == StudentStatus.REJECTED) {
                                recordList.add(record);
                            }
                        });
            } else {
                records.forEach(record -> {
                            StudentStatus status = record.getStatus();
                            if (status == StudentStatus.IN_PROCESS || status == StudentStatus.EXPELLED
                                    || status == StudentStatus.COMPLETED) {
                                recordList.add(record);
                            }
                        });
            }
        });

        PagesDelimiter<Record> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(recordList, limit);
        request.setAttribute(TRAINING_ID, trainingId);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGES, pageNumbersList);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        List<Record> resultRecordList = pagesDelimiter.composePageList(recordList, limit, offset);
        request.setAttribute(STUDENT_LIST, resultRecordList);

        String notifyMessage = request.getParameter(MESSAGE);
        if (notifyMessage != null) {
            request.setAttribute(NOTIFY_MESSAGE, notifyMessage);
        }
        return CommandResult.forward(STUDENTS_PAGE);
    }
}
