package org.example.exception;

public class FileFilterException extends RuntimeException{
    public FileFilterException() {
    }

    public FileFilterException(String message) {
        super(message);
    }

    public FileFilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileFilterException(Throwable cause) {
        super(cause);
    }
}
