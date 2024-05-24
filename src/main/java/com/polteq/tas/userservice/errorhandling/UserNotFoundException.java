package com.polteq.tas.userservice.errorhandling;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Could not find user " + email);
    }
}
