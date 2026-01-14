package com.gymmanagement.gym_management_api.Exceptions;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(String username) {
        super("Username already in use: " + username);
    }
}
