package com.polteq.tas.userservice.errorhandling;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
