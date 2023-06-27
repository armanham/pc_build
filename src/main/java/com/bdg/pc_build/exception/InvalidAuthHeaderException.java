package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAuthHeaderException extends ResponseStatusException {

    public InvalidAuthHeaderException() {
        super(HttpStatus.BAD_REQUEST, "Invalid authentication header: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}