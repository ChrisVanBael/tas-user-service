package com.polteq.tas.userservice.errorhandling;

public class UserExistsException extends RuntimeException {

    public UserExistsException(String email) {
        super("User " + email + " already exists");
    }
}
