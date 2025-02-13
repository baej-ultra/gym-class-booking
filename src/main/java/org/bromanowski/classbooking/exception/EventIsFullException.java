package org.bromanowski.classbooking.exception;

public class EventIsFullException extends RuntimeException {
    public EventIsFullException(String message) {
        super(message);
    }
}
