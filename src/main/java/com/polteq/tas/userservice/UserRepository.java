package com.polteq.tas.userservice;

import com.polteq.tas.userservice.models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData, String> {

}
