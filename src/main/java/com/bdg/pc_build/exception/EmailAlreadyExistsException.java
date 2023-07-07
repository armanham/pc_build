package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExistsException extends ResponseStatusException {

    public EmailAlreadyExistsException(HttpStatus httpStatus, String email) {
        super(httpStatus, "Email: " + email + " already exists: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}