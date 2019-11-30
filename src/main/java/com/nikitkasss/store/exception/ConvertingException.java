package com.nikitkasss.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConvertingException extends AbstractInvalidDataException {
    public ConvertingException() {
        super();
    }

    public ConvertingException(String message) {
        super(message);
    }

    public ConvertingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertingException(Throwable cause) {
        super(cause);
    }

    protected ConvertingException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
