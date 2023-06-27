package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
//todo patterns
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @JsonProperty(value = "first_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name is required")
    private String firstname;

    @JsonProperty(value = "last_name", required = true)
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name is required")
    private String lastname;

    @JsonProperty(value = "email", required = true)
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z]+.[A-Za-z]{2,}$\n", message  =  "Please provide a valid email address")
    private String email;

//    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-/*+=])(?=\\S+$).{8,}$\n",message = "Please provide a valid password")
    @JsonProperty(value = "password", required = true)
    private String password;
}