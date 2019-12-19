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
    private static final String SHOW_PROFILE = "showProfile";
    private static final String EDIT_PROFILE = "editProfile";
    private static final String SHOW_ADMINS = "showAdmins";
    private static final String SHOW_MENTORS = "showMentors";
    private static final String SHOW_STUDENTS = "showStudents";
    private static final String SHOW_TRAININGS = "showTrainings";
    private static final String SHOW_TRAINING_INFO = "showTrainingInfo";
    private static final String DELETE_TRAINING = "deleteTraining";
    private static final String SAVE_TRAINING = "saveTraining";
    private static final String SHOW_TRAINING_STUDENTS = "showTrainingStudents";
    private static final String SHOW_ASSIGNMENTS = "showAssignments";
    private static final String SAVE_ASSIGNMENT = "saveAssignment";
    private static final String DELETE_ASSIGNMENT = "deleteAssignment";
    private static final String SHOW_CONSULTATIONS = "showConsultations";
    private static final String SCHEDULE_CONSULTATION = "schedule";
    private static final String SHOW_CONSULTATION_INFO = "showConsultationInfo";
    private static final String INCLUDE_ASSIGNMENT = "includeAssignment";
    private static final String EXCLUDE_ASSIGNMENT = "excludeAssignment";
    private static final String DELETE_CONSULTATION = "deleteConsultation";
    private static final String SHOW_BALANCE = "showBalance";
    private static final String REFILL_BALANCE = "refillBalance";
    private static final String CHANGE_STUDENT_STATUS = "changeStudentStatus";
    private static final String RATE_STUDENT = "rateStudent";
    private static final String RATE_CONSULTATION_QUALITY = "rateConsultationQuality";
    private static final String RATE_STUDENT_PERFORMANCE = "rateStudentPerformance";
    private static final String APPLY_FOR_TRAINING = "applyForTraining";
    private static final String REQUEST_CONSULTATION = "requestConsultation";
    private static final String PAY_FOR_CONSULTATION = "payForConsultation";
    private static final String COMPLETE_PAYMENT = "completePayment";
    private static final String SHOW_STUDENT_REQUESTS = "showStudentRequests";
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
            switch (parameter) {
                case SHOW_ADMINS:
                    if (!UserRole.MAIN_ADMIN.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case SCHEDULE_CONSULTATION:
                case DELETE_TRAINING:
                case SAVE_TRAINING:
                case SHOW_MENTORS:
                case SHOW_STUDENTS:
                    if (!UserRole.ADMIN.equals(userRole) && !UserRole.MAIN_ADMIN.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case DELETE_ASSIGNMENT:
                case SAVE_ASSIGNMENT:
                case RATE_STUDENT_PERFORMANCE:
                case REQUEST_CONSULTATION:
                case RATE_STUDENT:
                    if (!UserRole.MENTOR.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case COMPLETE_PAYMENT:
                case PAY_FOR_CONSULTATION:
                case RATE_CONSULTATION_QUALITY:
                case INCLUDE_ASSIGNMENT:
                case EXCLUDE_ASSIGNMENT:
                case APPLY_FOR_TRAINING:
                case SHOW_STUDENT_REQUESTS:
                case REFILL_BALANCE:
                case SHOW_BALANCE:
                    if (!UserRole.STUDENT.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case CHANGE_STUDENT_STATUS:
                case SHOW_TRAINING_STUDENTS:
                case SHOW_TRAINING_INFO:
                    if (UserRole.STUDENT.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case DELETE_CONSULTATION:
                    if (UserRole.MENTOR.equals(userRole)) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
                case SHOW_PROFILE:
                case EDIT_PROFILE:
                case SHOW_TRAININGS:
                case SHOW_ASSIGNMENTS:
                case SHOW_CONSULTATIONS:
                case SHOW_CONSULTATION_INFO:
                    if (userRole == null) {
                        ((HttpServletResponse) servletResponse).sendError(ERROR_NUMBER);
                        return;
                    }
                    break;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
