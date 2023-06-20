package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @JsonProperty(value = "first_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name is required")
    String firstname;

    @JsonProperty(value = "last_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name is required")
    String lastname;

    @JsonProperty(value = "email", required = true)
    @Email(message = "Please provide a valid email address")
    String email;

    @JsonProperty(value = "password", required = true)
    String password;

    @JsonProperty(value = "role", required = true)
    String role;
}