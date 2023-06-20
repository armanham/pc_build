package com.bdg.pc_build.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FirstNameUpdateRequest {

    @JsonProperty("email")
    String email;

    @JsonProperty("new_first_name")
    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name is required")
    String newFirstName;

}
