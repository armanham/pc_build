package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoProductFoundInBuilderException extends ResponseStatusException {

    public NoProductFoundInBuilderException(HttpStatus httpStatus) {
        super(httpStatus, "No product found in Builder: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}