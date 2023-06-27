package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SameNameDifferentDescriptionException extends ResponseStatusException {

    public SameNameDifferentDescriptionException(Class clazz, String name) {
        super(clazz.getSimpleName() + " Product with the existing name:  " + name + "but different description not allowed: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}