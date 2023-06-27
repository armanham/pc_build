package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("Email: " + email + " already exists: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}