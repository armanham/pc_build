package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmailUpdateRequest(
        @JsonProperty(value = "email", required = true)
        String email,

        @JsonProperty(value = "new_email_name", required = true)
        String newEmail
) {
}