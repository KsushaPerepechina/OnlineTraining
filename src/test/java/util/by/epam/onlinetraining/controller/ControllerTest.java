package util.by.epam.onlinetraining.controller;

import by.epam.onlinetraining.controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    private static final String COMMAND = "command";
    private static final String CHANGE_LANGUAGE_COMMAND = "changeLanguage";
    private static final String REDIRECT_COMMAND = "controller?command=showMainPage";
    private static final String LANG = "lang";
    private static final String EN = "EN";
    private static final String QUERY_STRING = "command=changeLanguage&lang=EN&currentcommand=showMainPage";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @InjectMocks
    private Controller controller;

    @Before
    public void setup() {
        when(request.getParameter(COMMAND)).thenReturn(CHANGE_LANGUAGE_COMMAND);
        when(request.getParameter(LANG)).thenReturn(EN);
        when(request.getSession()).thenReturn(session);
        when(request.getQueryString()).thenReturn(QUERY_STRING);
    }

    @Test
    public void doGetTest() throws ServletException, IOException {
        controller.doGet(request, response);
        verify(response).sendRedirect(REDIRECT_COMMAND);
    }

    @Test
    public void doPostTest() throws ServletException, IOException {
        controller.doPost(request, response);
        verify(response).sendRedirect(REDIRECT_COMMAND);
    }
}
