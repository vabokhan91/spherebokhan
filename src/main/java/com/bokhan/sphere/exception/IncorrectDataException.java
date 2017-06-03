package com.bokhan.sphere.exception;

/**
 * Created by vbokh on 03.06.2017.
 */
public class IncorrectDataException extends Exception {
    public IncorrectDataException() {
        super();
    }

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(Throwable cause) {
        super(cause);
    }
}
