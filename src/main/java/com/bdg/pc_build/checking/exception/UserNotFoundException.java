package com.bdg.pc_build.checking.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super(email);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
