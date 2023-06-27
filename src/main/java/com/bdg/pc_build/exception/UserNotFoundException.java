package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {

    public UserNotFoundException(String email) {
        super(HttpStatus.NOT_FOUND, "User with email: " + email + " not found");
    }

    public UserNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "User with id: " + id + " not found");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}