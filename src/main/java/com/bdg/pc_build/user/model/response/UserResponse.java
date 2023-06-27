package com.bdg.pc_build.user.model.response;

import com.bdg.pc_build.user.model.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;

    public UserResponse(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}