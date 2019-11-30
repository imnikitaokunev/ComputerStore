package com.nikitkasss.store.exception.handler;

import com.nikitkasss.store.exception.AbstractInvalidDataException;
import com.nikitkasss.store.exception.ConvertingException;
import com.nikitkasss.store.exception.InvalidEntityDataException;
import com.nikitkasss.store.exception.NoSuchEntityException;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private String bodyOfResponse;

    @ExceptionHandler(value = {ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(HibernateException ex, WebRequest request) {
        bodyOfResponse = "Such owner or street doesn't exist. Check your entity.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ConvertingException.class, InvalidEntityDataException.class})
    protected ResponseEntity<Object> handleDataException(AbstractInvalidDataException ex, WebRequest request) {
        bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {NoSuchEntityException.class})
    protected ResponseEntity<Object> handleEntityException(NoSuchEntityException ex, WebRequest request) {
        bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        bodyOfResponse = "Unexpected error. Try again or check your entity.";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

