package com.nikitkasss.store.exception;

public abstract class AbstractInvalidDataException extends RuntimeException {
    public AbstractInvalidDataException() {
        super();
    }

    public AbstractInvalidDataException(String message) {
        super(message);
    }

    public AbstractInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractInvalidDataException(Throwable cause) {
        super(cause);
    }

    protected AbstractInvalidDataException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
