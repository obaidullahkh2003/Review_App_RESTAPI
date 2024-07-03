package com.Bau.api.exception;

public class ReviewNotFoundException extends RuntimeException {
    private static final long serialVerisionUID = 2L;
    public ReviewNotFoundException(String message) {
        super(message);

    }
}
