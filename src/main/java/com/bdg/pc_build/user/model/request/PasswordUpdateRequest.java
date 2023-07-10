package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.bdg.pc_build.util.Pattern.PASSWORD_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_PASSWORD_PATTERN;

public record PasswordUpdateRequest(
        @JsonProperty(value = "new_password", required = true)
        @NotBlank(message = "new_password can not be blank")
        @Pattern(regexp = PASSWORD_PATTERN, message = WRONG_PASSWORD_PATTERN)
        String newPassword
) {
}