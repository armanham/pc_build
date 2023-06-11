package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BlankOrEmptyStringException extends RuntimeException{
    public BlankOrEmptyStringException(Class clazz, String propertyName, String propertyValue) {
        super(clazz.getSimpleName() + " " + propertyName + " cannot be blank or empty. Passed: " + propertyValue);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}