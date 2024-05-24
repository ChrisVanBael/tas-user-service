package com.polteq.tas.userservice.validators;

import com.polteq.tas.userservice.models.UserData;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
public class UserDataValidator implements Validator {

    @Override
    public boolean supports(Class<?> cls) {
        log.info("Checking if UserData is supported");
        return UserData.class.equals(cls);
    }

    @Override
    public void validate(@NonNull Object obj, Errors errors) {
        UserData userData = (UserData) obj;

        if (userData.getEmail() != null || !userData.getEmail().isEmpty()) {
            if (!EmailValidator.isValid(userData.getEmail())) {
                errors.rejectValue("email", "email.invalid_format", "Email is not in the correct format");
            }
        }
    }
}
