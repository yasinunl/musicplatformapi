package com.domain.musicplatform.exception;

public class SuccessExceptionHandler extends RuntimeException{
    public SuccessExceptionHandler(String message) {
        super(message);
    }

    public SuccessExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public SuccessExceptionHandler(Throwable cause) {
        super(cause);
    }
}
