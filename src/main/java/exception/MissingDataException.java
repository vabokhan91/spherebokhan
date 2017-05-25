package exception;

/**
 * Created by vbokh on 25.05.2017.
 */
public class MissingDataException extends Exception {
    public MissingDataException() {
        super();
    }

    public MissingDataException(String message) {
        super(message);
    }

    public MissingDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissingDataException(Throwable cause) {
        super(cause);
    }
}
