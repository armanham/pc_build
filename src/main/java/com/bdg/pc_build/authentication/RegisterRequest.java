package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @JsonProperty(value = "first_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name is required")
    String firstName;

    @JsonProperty(value = "last_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name is required")
    String lastName;

    @JsonProperty(value = "email", required = true)
    @Email(message = "Please provide a valid email address")
    String email;

    @JsonProperty(value = "password", required = true)
    String password;
}