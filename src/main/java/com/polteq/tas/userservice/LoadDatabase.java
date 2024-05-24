package com.polteq.tas.userservice;

import com.polteq.tas.userservice.models.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
//            log.info("Preloading {}", repository.save(new UserData("max@rbr.com", "number1", "Max Verstappen", "+31612345678")));
//            log.info("Preloading {}", repository.save(new UserData("checo@rbr.com", "number11", "Checo Pérez", "+5287654321")));
        };
    }
}
