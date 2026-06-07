package com.carsrec.app.exception;

// Thrown when a user tries to use a resource that doesn't belong to them
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
