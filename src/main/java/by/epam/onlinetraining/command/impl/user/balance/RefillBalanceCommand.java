package by.epam.onlinetraining.command.impl.user.balance;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.TransactionService;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.TransactionServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Designed to refile balance.
 */
public class RefillBalanceCommand implements Command {
    private static final String SUM = "sum";
    private static final String ID = "id";
    private static final String BALANCE_COMMAND = "controller?command=showBalance";
    private static final String MESSAGE = "&message=";
    private static final String REFILLED_BALANCE = "refilledBalance";
    private static final String INVALID_SUM = "invalidSum";
    private static UserService userService = new UserServiceImpl();

    /**
     * Process the request, refile balance and generates a result of processing
     * in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     *
     * @param request  an {@link javax.servlet.http.HttpServletRequest} object that contains client request
     * @param response an {@link javax.servlet.http.HttpServletResponse} object that contains the response
     *                 the servlet sends to the client
     * @return A response in the form of {@link by.epam.onlinetraining.command.CommandResult} object.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(ID);
        String stringSum = request.getParameter(SUM);
        Validation validation = new Validation();
        if (!validation.isValidData(SUM, stringSum)) {
            return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + INVALID_SUM);
        }
        BigDecimal sum = new BigDecimal(stringSum);
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal balance = user.getBalance();
            BigDecimal newBalance = balance.add(sum);
            userService.refillBalance(id, newBalance);
        }
        return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + REFILLED_BALANCE);
    }
}
