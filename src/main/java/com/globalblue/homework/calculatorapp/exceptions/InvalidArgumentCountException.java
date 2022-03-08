package com.globalblue.homework.calculatorapp.exceptions;

public class InvalidArgumentCountException extends RuntimeException {
    public InvalidArgumentCountException() {
        super();
    }

    public InvalidArgumentCountException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidArgumentCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentCountException(String message) {
        super(message);
    }

    public InvalidArgumentCountException(Throwable cause) {
        super(cause);
    }
}
