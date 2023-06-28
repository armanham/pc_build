package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

//todo patterns
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
//    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z]+.[A-Za-z]{2,}$\n", message  =  "Please provide a valid email address")
    private String email;

    //    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-/*+=])(?=\\S+$).{8,}$\n",message = "Please provide a valid password")
    @JsonProperty(value = "password", required = true)
    private String password;
}