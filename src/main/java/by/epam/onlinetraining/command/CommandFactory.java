package by.epam.onlinetraining.command;

import by.epam.onlinetraining.command.admin.AdminStartPageCommand;
import by.epam.onlinetraining.command.admin.ChangeBlockingStatus;
import by.epam.onlinetraining.command.user.MainPageCommand;
import by.epam.onlinetraining.command.user.StartLogInCommand;
import by.epam.onlinetraining.command.user.balance.RefileBalanceCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static final String START_PAGE = "startPage";
    private static final String LOG_IN = "logIn";
    private static final String LOG_OUT = "signOut";
    private static final String SIGN_UP = "signUp";

    private static final String SHOW_PROFILE = "showProfile";
    private static final String SHOW_USERS = "showUsers";
    private static final String SHOW_BALANCE = "showBalance";
    private static final String REFILE_BALANCE = "refileBalance";
    private static final String MAIN_PAGE = "mainPage";
    private static final String ADMIN_MAIN_PAGE = "adminPage";
    private static final String CHANGE_LANGUAGE = "changeLanguage";
    private static final String EDIT_PROFILE = "editProfile";
    private static final String CHANGE_BLOCKING_STATUS = "changeBlockingStatus";
    private static final String START_LOG_IN = "startLogIn";

    public Command create(String command) {
        switch (command) {
            case LOG_IN:
                return new LogInCommand();
            case SHOW_PROFILE:
                return new ProfileCommand();
            case REFILE_BALANCE:
                return new RefileBalanceCommand();
            case MAIN_PAGE:
                return new MainPageCommand();
            case ADMIN_MAIN_PAGE:
                return new AdminStartPageCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case LOG_OUT:
                return new LogOutCommand();
            case EDIT_PROFILE:
                return new EditProfileCommand();
            case CHANGE_BLOCKING_STATUS:
                return new ChangeBlockingStatus();
            case START_PAGE:
                return new StartPageCommand();
            case SIGN_UP:
                return new SignUpCommand();
            case START_LOG_IN:
                return new StartLogInCommand();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
