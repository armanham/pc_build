package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class SameNameDifferentDescriptionException extends RuntimeException {

    public SameNameDifferentDescriptionException(Class clazz, String name) {
        super(clazz.getSimpleName() + " Product with the existing name:  " + name + "but different description not allowed: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}