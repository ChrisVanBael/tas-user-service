package com.polteq.tas.userservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserData {

    @Id
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9.-]{1,64}@[a-zA-Z0-9.-]{1,64}\\.[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
    private String name;

    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserData user))
            return false;
        return Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.email, this.password);
    }

    @Override
    public String toString() {
        return "User { email = '"
                + this.email
                + "', name = '"
                + this.name
                + "', phone = '" + this.phoneNumber;
    }
}
