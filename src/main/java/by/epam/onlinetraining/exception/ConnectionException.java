package by.epam.onlinetraining.exception;

public class ConnectionException extends Exception {
    private static final long serialVersionUID = 8543889584394850485L;

    public ConnectionException() {
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}
