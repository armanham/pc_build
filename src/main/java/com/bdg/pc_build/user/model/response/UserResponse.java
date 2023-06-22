package com.bdg.pc_build.user.model.response;

import com.bdg.pc_build.user.model.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class UserResponse {

    Long id;

    String firstName;

    String lastName;

    String email;

    public UserResponse(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
    }
}