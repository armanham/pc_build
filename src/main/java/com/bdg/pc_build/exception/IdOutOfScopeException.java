package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IdOutOfScopeException extends ResponseStatusException {

    public IdOutOfScopeException(Class aClass, Long id) {
        super(HttpStatus.CONFLICT, "The specified ID " + id + " is out of scope for product of type " + aClass.getSimpleName() + ": ");
    }

    public IdOutOfScopeException(Long id) {
        super(HttpStatus.CONFLICT, "The specified ID " + id + " is not in a valid range: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
