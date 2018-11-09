package com.userRegistrationService.userregistration.Exception;

public class UserAlreadyExistsException extends Exception {
    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
