package util.by.epam.onlinetraining.command.common;

import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.command.impl.common.ShowMainPageCommand;
import by.epam.onlinetraining.command.impl.common.ShowStartPageCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class ShowStartPageCommandTest {
    private static final String START_PAGE = "/index.jsp";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private ShowStartPageCommand showStartPageCommand;

    @Test
    public void executeTest() {
        CommandResult expected = new CommandResult(START_PAGE, false);
        CommandResult actual = showStartPageCommand.execute(request, response);
        Assert.assertEquals(actual, expected);
    }
}