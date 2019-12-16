package by.epam.onlinetraining.command.impl.user.training;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Record;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.RecordServiceImpl;
import by.epam.onlinetraining.util.PagesDelimiter;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowStudentRequestsCommand implements Command {
    private static final String ID = "id";
    private static final String LIMIT = "limit";
    private static final String PAGE_NUMBER = "pageNumber";
    private static final String PAGES = "pages";
    private static final String ERROR_PAGE = "/WEB-INF/page/error/Error404.jsp";
    private static final String RECORD_LIST = "recordList";
    private static final String STUDENT_REQUESTS_PAGE = "/WEB-INF/page/user/student/studentRequests.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int studentId = (Integer) session.getAttribute(ID);

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

        RecordServiceImpl recordService = new RecordServiceImpl();
        List<Record> recordList = recordService.findByStudentId(studentId);
        request.setAttribute(RECORD_LIST, recordList);

        PagesDelimiter<Record> pagesDelimiter = new PagesDelimiter<>();
        List<Integer> pageNumbersList = pagesDelimiter.composePageNumbersList(recordList, limit);
        request.setAttribute(LIMIT, limit);
        request.setAttribute(PAGE_NUMBER, pageNumber);
        request.setAttribute(PAGES, pageNumbersList);
        List<Record> resultRecordList = pagesDelimiter.composePageList(recordList, limit, offset);
        request.setAttribute(RECORD_LIST, resultRecordList);
        return CommandResult.forward(STUDENT_REQUESTS_PAGE);
    }
}
