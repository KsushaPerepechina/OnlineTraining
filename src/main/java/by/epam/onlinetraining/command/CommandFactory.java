package by.epam.onlinetraining.command;

import by.epam.onlinetraining.command.impl.training.assignment.DeleteAssignmentCommand;
import by.epam.onlinetraining.command.impl.training.assignment.SaveAssignmentCommand;
import by.epam.onlinetraining.command.impl.training.assignment.ShowAssignmentsCommand;
import by.epam.onlinetraining.command.impl.training.consultation.*;
import by.epam.onlinetraining.command.impl.training.consultation.assignment.ExcludeAssignmentCommand;
import by.epam.onlinetraining.command.impl.training.consultation.assignment.IncludeAssignmentCommand;
import by.epam.onlinetraining.command.impl.training.consultation.assignment.ShowConsultationInfoCommand;
import by.epam.onlinetraining.command.impl.training.main.DeleteTrainingCommand;
import by.epam.onlinetraining.command.impl.training.main.SaveTrainingCommand;
import by.epam.onlinetraining.command.impl.training.main.ShowTrainingInfoCommand;
import by.epam.onlinetraining.command.impl.training.main.ShowTrainingsCommand;
import by.epam.onlinetraining.command.impl.user.training.ApplyForTrainingCommand;
import by.epam.onlinetraining.command.impl.training.student.ChangeStudentStatusCommand;
import by.epam.onlinetraining.command.impl.training.student.RateStudentCommand;
import by.epam.onlinetraining.command.impl.training.student.ShowTrainingStudentsCommand;
import by.epam.onlinetraining.command.impl.user.authentication.LogOutCommand;
import by.epam.onlinetraining.command.impl.user.listing.ShowAdminsCommand;
import by.epam.onlinetraining.command.impl.user.listing.ShowMentorsCommand;
import by.epam.onlinetraining.command.impl.user.listing.ShowStudentsCommand;
import by.epam.onlinetraining.command.impl.common.*;
import by.epam.onlinetraining.command.impl.user.balance.ShowBalanceCommand;
import by.epam.onlinetraining.command.impl.user.authentication.LogInCommand;
import by.epam.onlinetraining.command.impl.common.ShowStartPageCommand;
import by.epam.onlinetraining.command.impl.user.authentication.SignUpCommand;
import by.epam.onlinetraining.command.impl.user.authentication.StartLogInCommand;
import by.epam.onlinetraining.command.impl.user.balance.RefillBalanceCommand;
import by.epam.onlinetraining.command.impl.user.profile.EditProfileCommand;
import by.epam.onlinetraining.command.impl.user.profile.ShowProfileCommand;
import by.epam.onlinetraining.command.impl.user.training.ShowStudentRequestsCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SHOW_START_PAGE = "showStartPage";
    private static final String SHOW_MAIN_PAGE = "showMainPage";
    private static final String LOG_IN = "logIn";
    private static final String START_LOG_IN = "startLogIn";
    private static final String LOG_OUT = "signOut";
    private static final String SIGN_UP = "signUp";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
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
    private static final String SHOW_STUDENT_REQUESTS = "showStudentRequests";
    private static final String UNSUPPORTED_OPERATION_MESSAGE = "Called operation is unsupported currently: ";

    public Command create(String command) {
        switch (command) {
            case SHOW_START_PAGE:
                return new ShowStartPageCommand();
            case SHOW_MAIN_PAGE:
                return new ShowMainPageCommand();
            case LOG_IN:
                return new LogInCommand();
            case START_LOG_IN:
                return new StartLogInCommand();
            case LOG_OUT:
                return new LogOutCommand();
            case SIGN_UP:
                return new SignUpCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case SHOW_PROFILE:
                return new ShowProfileCommand();
            case EDIT_PROFILE:
                return new EditProfileCommand();
            case SHOW_TRAININGS:
                return new ShowTrainingsCommand();
            case SHOW_TRAINING_INFO:
                return new ShowTrainingInfoCommand();
            case SAVE_TRAINING:
                return new SaveTrainingCommand();
            case DELETE_TRAINING:
                return new DeleteTrainingCommand();
            case SHOW_ASSIGNMENTS:
                return new ShowAssignmentsCommand();
            case SAVE_ASSIGNMENT:
                return new SaveAssignmentCommand();
            case DELETE_ASSIGNMENT:
                return new DeleteAssignmentCommand();
            case SHOW_CONSULTATIONS:
                return new ShowConsultationsCommand();
            case REQUEST_CONSULTATION:
                return new RequestConsultationCommand();
            case PAY_FOR_CONSULTATION:
                return new PayForConsultationCommand();
            case SHOW_CONSULTATION_INFO:
                return new ShowConsultationInfoCommand();
            case INCLUDE_ASSIGNMENT:
                return new IncludeAssignmentCommand();
            case EXCLUDE_ASSIGNMENT:
                return new ExcludeAssignmentCommand();
            case DELETE_CONSULTATION:
                return new DeleteConsultationCommand();
            case SHOW_TRAINING_STUDENTS:
                return new ShowTrainingStudentsCommand();
            case SHOW_ADMINS:
                return new ShowAdminsCommand();
            case SHOW_MENTORS:
                return new ShowMentorsCommand();
            case SHOW_STUDENTS:
                return new ShowStudentsCommand();
            case SHOW_BALANCE:
                return new ShowBalanceCommand();
            case REFILL_BALANCE:
                return new RefillBalanceCommand();
            case CHANGE_STUDENT_STATUS:
                return new ChangeStudentStatusCommand();
            case RATE_STUDENT:
                return new RateStudentCommand();
            case RATE_STUDENT_PERFORMANCE:
                return new RateStudentPerformanceCommand();
            case RATE_CONSULTATION_QUALITY:
                return new RateConsultationQualityCommand();
            case APPLY_FOR_TRAINING:
                return new ApplyForTrainingCommand();
            case SHOW_STUDENT_REQUESTS:
                return new ShowStudentRequestsCommand();
            default:
                LOGGER.error(UNSUPPORTED_OPERATION_MESSAGE + command);
                throw new UnsupportedOperationException();
        }
    }
}
