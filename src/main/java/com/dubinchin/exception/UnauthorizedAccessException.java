package com.dubinchin.exception;

public class UnauthorizedAccessException extends RuntimeException{

    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
