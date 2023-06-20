package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Length(min = 3, max = 24)
    String firstname;

    @JsonProperty(value = "last_name", required = true)
    @Length(min = 3, max = 24)
    String lastname;

    @JsonProperty(value = "email", required = true)
    String email;

    @JsonProperty(value = "password", required = true)
    String password;

    @JsonProperty(value = "role", required = true)
    String role;
}