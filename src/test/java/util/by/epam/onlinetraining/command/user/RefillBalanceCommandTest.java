package util.by.epam.onlinetraining.command.user;

import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.command.impl.user.balance.RefillBalanceCommand;
import by.epam.onlinetraining.command.impl.user.balance.ShowBalanceCommand;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.TransactionService;
import by.epam.onlinetraining.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RefillBalanceCommandTest {
    private static final String ID = "id";
    private static final String SUM = "sum";
    private static final String SHOW_BALANCE_COMMAND = "controller?command=showBalance&message=refilledBalance";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private UserService userService;
    @InjectMocks
    private RefillBalanceCommand refillBalanceCommand;

    @Before
    public void setup() throws ServiceException {
        User user = new User(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ID)).thenReturn(user.getId());
        when(request.getParameter(SUM)).thenReturn("5");
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
    }

    @Test
    public void executeTest() throws ServiceException {
        CommandResult expected = new CommandResult(SHOW_BALANCE_COMMAND, true);
        CommandResult actual = refillBalanceCommand.execute(request, response);
        Assert.assertEquals(actual, expected);
    }
}