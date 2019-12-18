package util.by.epam.onlinetraining.command.common;

import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.command.impl.common.ShowMainPageCommand;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class ShowMainPageCommandTest {
    private static final String MAIN_PAGE = "/WEB-INF/page/common/mainPage.jsp";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private ShowMainPageCommand showMainPageCommand;

    @Test
    public void executeTest() {
        CommandResult expected = new CommandResult(MAIN_PAGE, false);
        CommandResult actual = showMainPageCommand.execute(request, response);
        Assert.assertEquals(actual, expected);
    }
}
