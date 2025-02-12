package org.bromanowski.classbooking.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.bromanowski.classbooking.exception.AlreadySignedUpException;
import org.bromanowski.classbooking.exception.EmailExistsException;
import org.bromanowski.classbooking.exception.response.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleException(EntityNotFoundException exception) {
        StandardErrorResponse response = new StandardErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleException(EmailExistsException exception) {
        StandardErrorResponse response = new StandardErrorResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleException(AccessDeniedException exception) {
        StandardErrorResponse response = new StandardErrorResponse();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleException(AlreadySignedUpException exception) {
        StandardErrorResponse response = new StandardErrorResponse();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<StandardErrorResponse> handleException(Exception exception) {
        StandardErrorResponse response = new StandardErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}