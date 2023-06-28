package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoProductFoundInBuilderException extends ResponseStatusException {

    public NoProductFoundInBuilderException() {
        super(HttpStatus.BAD_REQUEST, "No product found in Builder: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
