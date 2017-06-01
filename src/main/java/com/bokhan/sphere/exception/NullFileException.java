package com.bokhan.sphere.exception;

/**
 * Created by vbokh on 30.05.2017.
 */
public class NullFileException extends Exception {
    public NullFileException() {
        super();
    }

    public NullFileException(String message) {
        super(message);
    }

    public NullFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullFileException(Throwable cause) {
        super(cause);
    }
}
