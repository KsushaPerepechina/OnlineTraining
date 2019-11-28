package by.epam.onlinetraining.command.impl.student.balance;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TransactionService;
import by.epam.onlinetraining.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class ShowBalanceCommand implements Command {
    private static final String BALANCE_PAGE = "/WEB-INF/page/student/balance.jsp";
    private static final String ID = "id";
    private static final String STUDENT = "student";
    private static final String TRANSACTION_LIST = "transactionList";
    private static final String MESSAGE = "message";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(ID);
        TransactionService transactionService = new TransactionService();
        List<Transaction> transactionList = transactionService.findById(id);
        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User student = optionalUser.get();
            request.setAttribute(TRANSACTION_LIST, transactionList);
            request.setAttribute(STUDENT, student);
        }
        String message = request.getParameter(MESSAGE);
        if (message != null) {
            request.setAttribute(MESSAGE, message);
        }
        return CommandResult.forward(BALANCE_PAGE);
    }
}
