package by.epam.onlinetraining.filter;

import by.epam.onlinetraining.entity.type.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    private static final String ROLE = "role";
    private static final String COMMAND = "command";
    private static final String SHOW_USERS = "showUsers";
    private static final Integer ERROR_NUMBER = 403;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String parameter = servletRequest.getParameter(COMMAND);
        if (parameter != null) {
            HttpSession session = ((HttpServletRequest) servletRequest).getSession();
            UserRole userRole = (UserRole) session.getAttribute(ROLE);
            if (parameter.equals(SHOW_USERS)) {// || parameter.equals(...)
                if (!userRole.equals(UserRole.ADMIN)) {
                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                    return;
                }
            } /*else if (parameter.equals(MAIN_PAGE)) {
                if (userRole.equals(UserRole.ADMIN)) {
                    ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                    return;
                }
            }*/
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
