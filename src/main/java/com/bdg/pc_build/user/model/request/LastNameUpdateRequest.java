package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

public record LastNameUpdateRequest(

        @JsonProperty(value = "email", required = true)
        String email,

        @JsonProperty(value = "new_last_name", required = true)
        @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name is required")
        String newLastName
) {
}