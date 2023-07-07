package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTokenException extends ResponseStatusException {

    public InvalidTokenException(HttpStatus httpStatus) {
        super(httpStatus, "Provided token is not valid: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
