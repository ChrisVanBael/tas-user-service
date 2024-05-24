package com.polteq.tas.userservice;

import com.polteq.tas.userservice.errorhandling.UserExistsException;
import com.polteq.tas.userservice.errorhandling.UserNotFoundException;
import com.polteq.tas.userservice.models.UserData;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class UserServiceController {

    private final UserRepository repository;

    UserServiceController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/user", produces = "application/json")
    List<UserData> getAllUsers() {
        log.info("Getting all users");
        return repository.findAll();
    }

    @PostMapping(value = "/user", produces = "application/json")
    @CrossOrigin
    UserData createNewUser(@Valid @RequestBody UserData newUser) {
        log.info("Create new user for email '{}'", newUser.getEmail());

        Optional<UserData> user = repository.findById(newUser.getEmail());

        if (user.isPresent()) {
            log.info("User for email '{}' already exists", newUser.getEmail());
            throw new UserExistsException(newUser.getEmail());
        }

        UserData result = repository.save(newUser);
        log.info("User for email '{}' created", newUser.getEmail());
        return result;
    }

    @GetMapping("/users/{email}")
    UserData getOneUser(@PathVariable String email) {
        log.info("Getting user with email: {}", email);
        return repository.findById(email) //
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @PutMapping("/users/{email}")
    UserData updateUser(@RequestBody UserData userToUpdate, @PathVariable String email) {
        log.info("Updating user with email: {}", email);
        return repository.findById(email) //
                .map(employee -> {
                    employee.setPassword(userToUpdate.getPassword());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    userToUpdate.setEmail(email);
                    return repository.save(userToUpdate);
                });
    }

    @DeleteMapping("/users/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable String email) {
        log.info("Deleting user with email {} ...", email);
        if (repository.existsById(email)) {
            repository.deleteById(email);
            log.info("User with email {} deleted", email);
        } else {
            throw new UserNotFoundException(email);
        }
    }
}
