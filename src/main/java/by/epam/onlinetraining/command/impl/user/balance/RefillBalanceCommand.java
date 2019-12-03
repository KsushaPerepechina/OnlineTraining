package by.epam.onlinetraining.command.impl.user.balance;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.impl.TransactionService;
import by.epam.onlinetraining.service.impl.UserService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class RefillBalanceCommand implements Command {
    private static final String SUM = "sum";
    private static final String ID = "id";
    private static final String BALANCE_COMMAND = "controller?command=showBalance";
    private static final String MESSAGE = "&message=";
    private static final String REFILLED_BALANCE = "refilledBalance";
    private static final String INVALID_SUM = "invalidSum";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        LocalDate currentDate = LocalDate.now();
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(ID);
        String stringSum = request.getParameter(SUM);
        Validation validation = new Validation();
        if (!validation.isValidData(SUM, stringSum)) {
            return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + INVALID_SUM);
        }
        BigDecimal sum = new BigDecimal(stringSum);
        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal balance = user.getBalance();
            BigDecimal newBalance = balance.add(sum);//TODO transactions
            userService.updateBalance(id, newBalance);
            TransactionService transactionService = new TransactionService();
            transactionService.addOperation(id, OperationType.REFILL, currentDate, sum);
        }
        return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + REFILLED_BALANCE);
    }
}
