package com.bdg.pc_build.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email: " + email + " not found");
    }

    public UserNotFoundException(Long id) {
        super("User with id: " + id + " not found");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
