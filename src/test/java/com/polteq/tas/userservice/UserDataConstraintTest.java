package com.polteq.tas.userservice;

import com.polteq.tas.userservice.models.UserData;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest()
public class UserDataConstraintTest {

    @Autowired
    private TestEntityManager tem;
    @Autowired
    private UserRepository userRepository;

    private UserData createValidUser() {
        return new UserData("emailname@validdomain.com", "password", "name", "12345");
    }

    @Test
    public void findById() {

        // given
        UserData userToSave = createValidUser();
        tem.persistAndFlush(userToSave);
        // when
        UserData foundUser = tem.find(UserData.class, userToSave.getEmail());
        // then
        assertEquals(userToSave.getEmail(), foundUser.getEmail());
    }

    @Test
    public void checkEmailIsMandatory() {
        // create a user with an empty email
        // expect a ConstraintViolationException after saving the user
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            UserData u = createValidUser();
            u.setEmail("");
            tem.persistAndFlush(u);
        });

        // get the violations
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        // check if there is one violation
        assertEquals(2, violations.size(), "There should be one violation");

        // check if the message of the violation is correct
        Iterator<ConstraintViolation<?>> iterator = violations.iterator();
        String expectedMessage = "Email is mandatory";
        assertEquals(iterator.next().getMessage(), expectedMessage);
    }

    private static Stream<Arguments> provideInvalidEmails() {
        return Stream.of(
                Arguments.of("test"),
                Arguments.of("test@"),
                Arguments.of("test@."),
                Arguments.of("test@.com"),
                Arguments.of("test@com"),
                Arguments.of("test.com"),
                Arguments.of("test@com."),
                Arguments.of("test@a.c")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmails")
    public void checkInvalidEmail(String inValidEmail) {
        // create a user with an empty email
        // expect a ConstraintViolationException after saving the user
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            UserData u = createValidUser();
            u.setEmail(inValidEmail);
            tem.persistAndFlush(u);
        });

        // get the violations
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        // check if there is one violation
        assertEquals(1, violations.size(), "There should be one violation");

        // check if the message of the violation is correct
        Iterator<ConstraintViolation<?>> iterator = violations.iterator();
        String expectedMessage = "Email is not valid";
        assertEquals(expectedMessage, iterator.next().getMessage());
    }

    private static Stream<Arguments> provideValidEmails() {

        String uniqueEmailPart = UUID.randomUUID().toString();

        return Stream.of(
                Arguments.of(uniqueEmailPart + "@testingemails.com")
        );
    }

    @ParameterizedTest(name="{index} {0} should be a valid email")
    @MethodSource("provideValidEmails")
    public void checkValidEmail(String validEmail) {
        // create a user with an empty email
        // expect a ConstraintViolationException after saving the user
        assertDoesNotThrow(() -> {
            UserData u = createValidUser();
            u.setEmail(validEmail);
            tem.persistAndFlush(u);
        });
    }

    @Test
    public void checkPasswordIsMandatory() {
        // create a user with an empty email
        // expect a ConstraintViolationException after saving the user
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            UserData u = createValidUser();
            u.setPassword("");
            tem.persistAndFlush(u);
        });

        // get the violations
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        // check if there is one violation
        assertEquals(violations.size(), 1, "There should be one violation");

        // check if the message of the violation is correct
        Iterator<ConstraintViolation<?>> iterator = violations.iterator();
        String expectedMessage = "Password is required";
        assertEquals(expectedMessage, iterator.next().getMessage());
    }

    @Test
    public void checkNameIsMandatory() {
        // create a user with an empty email
        // expect a ConstraintViolationException after saving the user
        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
            UserData u = createValidUser();
            u.setName("");
            tem.persistAndFlush(u);
        });

        // get the violations
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        // check if there is one violation
        assertEquals(violations.size(), 1, "There should be one violation");

        // check if the message of the violation is correct
        Iterator<ConstraintViolation<?>> iterator = violations.iterator();
        String expectedMessage = "Name is required";
        assertEquals(expectedMessage, iterator.next().getMessage());
    }

}
