package com.Jy714.computerStore.controller.ex;

public class ValidCodeNotMatchException extends RuntimeException {
    public ValidCodeNotMatchException() {
        super();
    }

    public ValidCodeNotMatchException(String message) {
        super(message);
    }

    public ValidCodeNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidCodeNotMatchException(Throwable cause) {
        super(cause);
    }

    protected ValidCodeNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
