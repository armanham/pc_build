package com.bdg.pc_build.user.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmailUpdateRequest(
        @JsonProperty(value = "new_email", required = true)
        String newEmail
) {
}