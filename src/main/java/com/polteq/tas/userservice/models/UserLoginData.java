package com.polteq.tas.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginData {
    private String email;
    private String password;
}
