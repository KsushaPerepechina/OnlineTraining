package util.by.epam.onlinetraining.command.user;

import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.command.impl.common.ChangeLanguageCommand;
import by.epam.onlinetraining.command.impl.user.balance.ShowBalanceCommand;
import by.epam.onlinetraining.entity.Transaction;
import by.epam.onlinetraining.entity.User;
import by.epam.onlinetraining.exception.ServiceException;
import by.epam.onlinetraining.service.TransactionService;
import by.epam.onlinetraining.service.UserService;
import by.epam.onlinetraining.service.impl.TransactionServiceImpl;
import by.epam.onlinetraining.service.impl.UserServiceImpl;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowBalanceCommandTest {
    private static final String ID = "id";
    private static final String MESSAGE = "message";
    private static final String BALANCE_PAGE = "/WEB-INF/page/user/balance.jsp";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private TransactionServiceImpl transactionService;
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private ShowBalanceCommand showBalanceCommand;

    @Before
    public void setup() throws ServiceException {
        User user = new User(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ID)).thenReturn(user.getId());
        when(transactionService.findByPayerId(user.getId())).thenReturn(Collections.singletonList(new Transaction()));
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        when(request.getParameter(MESSAGE)).thenReturn(null);
    }

    @Test
    public void executeTest() throws ServiceException {
        CommandResult expected = new CommandResult(BALANCE_PAGE, false);
        CommandResult actual = showBalanceCommand.execute(request, response);
        Assert.assertEquals(actual, expected);
    }
}