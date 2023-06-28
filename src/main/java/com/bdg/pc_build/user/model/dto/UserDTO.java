package com.bdg.pc_build.user.model.dto;

import com.bdg.pc_build.user.enumerations.Role;
import com.bdg.pc_build.user.model.entity.User;
import lombok.Getter;

@Getter
public class UserDTO {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Role role;

    public UserDTO(final User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}