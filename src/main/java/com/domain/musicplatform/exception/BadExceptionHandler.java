package com.domain.musicplatform.exception;

public class BadExceptionHandler extends RuntimeException{
    public BadExceptionHandler(String message) {
        super(message);
    }

    public BadExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public BadExceptionHandler(Throwable cause) {
        super(cause);
    }
}
