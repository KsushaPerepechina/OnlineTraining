package by.epam.onlinetraining.command.user.balance;

import by.epam.onlinetraining.command.Command;
import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.entity.type.OperationType;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.TransactionService;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.validation.Validation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

public class RefileBalanceCommand implements Command {
    private static final String SUM = "sumUp";
    private static final String ID = "id";
    private static final String BALANCE_COMMAND = "by/epam/onlinetraining/controller";
    private static final String MESSAGE = "&message=";
    private static final String REFILE_BALANCE = "refileBalance";
    private static final String INVALID_SUM = "invalidSum";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Date currentDate = new Date(System.currentTimeMillis());

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute(ID);

        String stringSum = request.getParameter(SUM);

        Validation validation = new Validation();
        if (!validation.isValidData(SUM, stringSum)) {
            return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + INVALID_SUM);
        }


        BigDecimal sumUp = new BigDecimal(stringSum);

        UserService userService = new UserService();
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            BigDecimal balance = user.getBalance();
            BigDecimal newBalance = balance.add(sumUp);
            userService.updateBalance(id, newBalance);
            TransactionService transactionService = new TransactionService();
            transactionService.addOperations(id, OperationType.MONEY_TRANSFER, currentDate, sumUp);
        }

        return CommandResult.redirect(BALANCE_COMMAND + MESSAGE + REFILE_BALANCE);
    }
}
