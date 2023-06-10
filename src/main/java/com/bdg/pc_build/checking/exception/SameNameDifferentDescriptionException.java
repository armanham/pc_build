package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class SameNameDifferentDescriptionException extends RuntimeException {

    public SameNameDifferentDescriptionException(Class clazz) {
        super(clazz.getSimpleName() + " Product with the existing name but different description not allowed: ");
    }
}