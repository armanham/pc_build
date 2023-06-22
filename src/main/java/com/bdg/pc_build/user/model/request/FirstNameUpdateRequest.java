package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

public record FirstNameUpdateRequest(


        @JsonProperty(value = "new_first_name", required = true)
        @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The first name is required")
        String newFirstName
) {
}