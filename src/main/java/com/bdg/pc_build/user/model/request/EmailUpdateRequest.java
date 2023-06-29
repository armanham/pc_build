package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.bdg.pc_build.util.Pattern.EMAIL_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_EMAIL_PATTERN;

public record EmailUpdateRequest(
        @JsonProperty(value = "new_email", required = true)
        @NotBlank(message = "new_email can not be blank")
        @Pattern(regexp = EMAIL_PATTERN, message = WRONG_EMAIL_PATTERN)
        String newEmail
) {
}