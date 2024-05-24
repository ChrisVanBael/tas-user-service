package com.polteq.tas.userservice.errorhandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class UserControllerExceptionAdvice {

    @ExceptionHandler(value = InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public @ResponseBody ErrorResponse handleInvalidCredentialsException(InvalidPasswordException e) {
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleUserNotFoundException(UserNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody ErrorResponse handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        boolean firstError = true;
        List<String> errors = new ArrayList<>();

        exceptions.getBindingResult().getAllErrors().forEach((error) -> {
            log.info("Validation error: {}", error);
            errors.add(error.getDefaultMessage());
        });

        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), String.join(", ", errors));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserExistsException.class)
    public @ResponseBody ErrorResponse handleUserExistsExceptions(UserExistsException exceptions) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exceptions.getMessage());
    }

}
