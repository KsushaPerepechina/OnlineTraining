package by.epam.onlinetraining.command.impl.user.balance;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TransactionServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * Designed to display balance and history of operations with the balance.
 */
public class ShowBalanceCommand implements Command {
    private static final String BALANCE_PAGE = "/WEB-INF/page/user/balance.jsp";
    private static final String ID = "id";
    private static final String STUDENT = "student";
    private static final String TRANSACTION_LIST = "transactionList";
    private static final String MESSAGE = "message";

    /**
     * Process the request, display balance and history of operations with the balance and generates a result
     * of processing in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request  an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(ID);
        TransactionServiceImpl transactionService = new TransactionServiceImpl();
        List<Transaction> transactionList = transactionService.findByPayerId(id);
        UserServiceImpl userService = new UserServiceImpl();
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
