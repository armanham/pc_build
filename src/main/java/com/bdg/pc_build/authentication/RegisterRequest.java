package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import static com.bdg.pc_build.util.Pattern.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @JsonProperty(value = "first_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name must be contain only letters, length of characters must be between 2 and 50 inclusive: ")
    private String firstname;

    @JsonProperty(value = "last_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name must be contain only letters, length of characters must be between 2 and 50 inclusive: ")
    private String lastname;

    @JsonProperty(value = "email", required = true)
    @NotBlank(message = "email can not be blank")
    @Pattern(regexp = EMAIL_PATTERN, message = WRONG_EMAIL_PATTERN)
    private String email;

    @Pattern(regexp = PASSWORD_PATTERN, message = WRONG_PASSWORD_PATTERN)
    @JsonProperty(value = "password", required = true)
    private String password;
}