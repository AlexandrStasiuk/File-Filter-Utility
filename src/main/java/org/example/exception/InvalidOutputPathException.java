package org.example.exception;

public class InvalidOutputPathException extends RuntimeException{
    public InvalidOutputPathException() {
    }

    public InvalidOutputPathException(String message) {
        super(message);
    }

    public InvalidOutputPathException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOutputPathException(Throwable cause) {
        super(cause);
    }
}
