package by.epam.onlinetraining.command;

import by.epam.onlinetraining.command.impl.admin.training.DeleteTrainingCommand;
import by.epam.onlinetraining.command.impl.admin.user.ShowAdminsCommand;
import by.epam.onlinetraining.command.impl.admin.user.ShowMentorsCommand;
import by.epam.onlinetraining.command.impl.admin.user.ShowStudentsCommand;
import by.epam.onlinetraining.command.impl.admin.training.EditTrainingCommand;
import by.epam.onlinetraining.command.impl.admin.training.ShowTrainingsCommand;
import by.epam.onlinetraining.command.impl.common.*;
import by.epam.onlinetraining.command.impl.mentor.ShowTrainingStudentsCommand;
import by.epam.onlinetraining.command.impl.student.balance.ShowBalanceCommand;
import by.epam.onlinetraining.command.impl.unauthorized.LogInCommand;
import by.epam.onlinetraining.command.impl.unauthorized.ShowStartPageCommand;
import by.epam.onlinetraining.command.impl.unauthorized.SignUpCommand;
import by.epam.onlinetraining.command.impl.unauthorized.StartLogInCommand;
import by.epam.onlinetraining.command.impl.student.balance.RefillBalanceCommand;
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
    private static final String ADD_TRAINING = "addTraining";
    private static final String EDIT_TRAINING = "editTraining";
    private static final String SHOW_TRAINING_STUDENTS = "showTrainingStudents";
    private static final String SHOW_BALANCE = "showBalance";
    private static final String REFILL_BALANCE = "refillBalance";
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
            case DELETE_TRAINING:
                return new DeleteTrainingCommand();
            case EDIT_TRAINING:
                return new EditTrainingCommand();
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
            default:
                LOGGER.error(UNSUPPORTED_OPERATION_MESSAGE + command);
                throw new UnsupportedOperationException();
        }
    }
}
