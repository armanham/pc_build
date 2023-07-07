package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAuthHeaderException extends ResponseStatusException {

    public InvalidAuthHeaderException(HttpStatus httpStatus) {
        super(httpStatus, "Invalid authentication header: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}