package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

public record LastNameUpdateRequest(
        @JsonProperty(value = "new_last_name", required = true)
        @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "The last name must be contain only letters, length of characters must be between 2 and 50 inclusive: ")
        String newLastName
) {
}