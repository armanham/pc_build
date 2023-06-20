package com.bdg.pc_build.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequest {

    @JsonProperty(value = "email", required = true)
    String email;

    @JsonProperty(value = "password", required = true)
    String password;
}