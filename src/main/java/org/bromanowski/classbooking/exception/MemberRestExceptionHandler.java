package org.bromanowski.classbooking.exception;

import org.bromanowski.classbooking.exception.response.MemberErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MemberRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<MemberErrorResponse> handleException(MemberNotFoundException exception) {

        MemberErrorResponse response = new MemberErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
