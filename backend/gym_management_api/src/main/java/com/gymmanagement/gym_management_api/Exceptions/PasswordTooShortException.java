package com.gymmanagement.gym_management_api.Exceptions;

public class PasswordTooShortException extends RuntimeException {
    public PasswordTooShortException() {
        super("Password must be at least 8 characters long.");
    }
}
