package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdOutOfScopeException extends ResponseStatusException {

    public IdOutOfScopeException(HttpStatus httpStatus, Class aClass, Long id) {
        super(httpStatus, "The specified ID " + id + " is out of scope for product of type " + aClass.getSimpleName() + ": ");
    }

    public IdOutOfScopeException(HttpStatus httpStatus, Long id) {
        super(httpStatus, "The specified ID " + id + " is not in a valid range: ");
    }

    public IdOutOfScopeException(HttpStatus httpStatus) {
        super(httpStatus, "The specified ID must be positive");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}