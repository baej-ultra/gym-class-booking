package org.bromanowski.classbooking.exception;

public class AlreadySignedUpException extends RuntimeException {
    public AlreadySignedUpException(String message) {
        super(message);
    }
}
