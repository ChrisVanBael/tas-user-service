package com.polteq.tas.userservice.validators;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class EmailValidator {

    // email requirements
    // - starts with 1 or more letters
    // - followed by an @
    // - followed by 1 or more letters
    // - followed by a .
    // - followed by 2 to 6 letters

//    public static final String EMAIL_REGEX = "[a-zA-Z0-9_+-]@[a-zA-Z0-9-+]\\.[a-zA-Z]{2,6}";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9]{1,64}@[a-zA-Z0-9]{1,64}\\.[a-zA-Z]{2,6}$";

    public static boolean isValid(String emailAddress) {
        return patternMatches(emailAddress, EMAIL_REGEX);
    }


    private static boolean patternMatches(String emailAddress, String regexPattern) {
        log.info("Checking if email address {} matches pattern {}", emailAddress, regexPattern);
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
