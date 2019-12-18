package by.epam.onlinetraining.command;

import java.util.StringJoiner;

/**
 * Designed to display the result of the command processing.
 */
public class CommandResult {
    private final String page;
    private final boolean redirect;

    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResult that = (CommandResult) o;
        return redirect == that.redirect &&
                page.equals(that.page);
    }

    @Override
    public String toString() {//TODO
        return new StringJoiner(", ", CommandResult.class.getSimpleName() + "[", "]")
                .add("page='" + page + "'")
                .add("redirect=" + redirect)
                .toString();
    }
}
