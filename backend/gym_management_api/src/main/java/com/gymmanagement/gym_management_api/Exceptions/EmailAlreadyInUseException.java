package com.gymmanagement.gym_management_api.Exceptions;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String email) {
      super("Email already in use: " + email);
    }
}
