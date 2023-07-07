package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SameNameDifferentDescriptionException extends ResponseStatusException {

    public SameNameDifferentDescriptionException(HttpStatus httpStatus, Class clazz, String name) {
        super(httpStatus, clazz.getSimpleName() + " Product with the existing name:  " + name + "but with different description is not allowed: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}