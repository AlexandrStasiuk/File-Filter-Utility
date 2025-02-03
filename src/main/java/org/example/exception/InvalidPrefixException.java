package org.example.exception;

public class InvalidPrefixException extends RuntimeException{
    public InvalidPrefixException() {
    }

    public InvalidPrefixException(String message) {
        super(message);
    }

    public InvalidPrefixException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPrefixException(Throwable cause) {
        super(cause);
    }
}
