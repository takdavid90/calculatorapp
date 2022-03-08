package com.globalblue.homework.calculatorapp.exceptions;

public class InvalidVatRateException extends RuntimeException {

    public InvalidVatRateException() {
        super();
    }

    public InvalidVatRateException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidVatRateException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVatRateException(String message) {
        super(message);
    }

    public InvalidVatRateException(Throwable cause) {
        super(cause);
    }
}
