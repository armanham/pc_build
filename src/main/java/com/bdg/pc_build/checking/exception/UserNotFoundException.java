package com.bdg.pc_build.checking.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("User with email: " + email + " not found");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
