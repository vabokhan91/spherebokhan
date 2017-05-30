package com.bokhan.sphere.exception;

/**
 * Created by vbokh on 24.05.2017.
 */
public class IncorrectDataTypeException extends Exception {
    public IncorrectDataTypeException() {
        super();
    }

    public IncorrectDataTypeException(String message) {
        super(message);
    }

    public IncorrectDataTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataTypeException(Throwable cause) {
        super(cause);
    }
}
