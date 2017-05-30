package com.bokhan.sphere.exception;

/**
 * Created by vbokh on 28.05.2017.
 */
public class RedunduntDataException extends Exception {
    public RedunduntDataException() {
        super();
    }

    public RedunduntDataException(String message) {
        super(message);
    }

    public RedunduntDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedunduntDataException(Throwable cause) {
        super(cause);
    }
}
