package org.example.exception;

public class InvalidFileFormatException extends RuntimeException{
    public InvalidFileFormatException() {
    }

    public InvalidFileFormatException(String message) {
        super(message);
    }

    public InvalidFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileFormatException(Throwable cause) {
        super(cause);
    }
}
