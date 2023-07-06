package com.bdg.pc_build.desire_log.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DesireLogCreationRequest(
        @JsonProperty(value = "component_type")
        String componentType,

        @JsonProperty(value = "name", required = true)
        @Length(min = 2, message = "length of name must be greater than or equal 2")
        @NotBlank(message = "name cannot be blank")
        String name,

        @JsonProperty(value = "description", required = true)
        @Length(min = 8, message = "length of description must be greater than or equal 8")
        @NotBlank(message = "description cannot be blank")
        String description
) {
}