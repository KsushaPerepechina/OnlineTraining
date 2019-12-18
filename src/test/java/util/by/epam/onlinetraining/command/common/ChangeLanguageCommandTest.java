package util.by.epam.onlinetraining.command.common;

import by.epam.onlinetraining.command.CommandResult;
import by.epam.onlinetraining.command.impl.common.ChangeLanguageCommand;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLanguageCommandTest {
    private static final String LANG = "lang";
    private static final String EN = "EN";
    private static final String QUERY_STRING = "command=changeLanguage&lang=EN&current";
    private static final String REDIRECT_COMMAND = "controller?command=showMainPage";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @InjectMocks
    private ChangeLanguageCommand changeLanguageCommand;

    @Before
    public void setup() {
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(LANG)).thenReturn(EN);
        when(request.getQueryString()).thenReturn(QUERY_STRING);
    }

    @Test
    public void executeTest() {
        CommandResult expected = new CommandResult(REDIRECT_COMMAND, true);
        CommandResult actual = changeLanguageCommand.execute(request, response);
        Assert.assertEquals(actual, expected);
    }
}
