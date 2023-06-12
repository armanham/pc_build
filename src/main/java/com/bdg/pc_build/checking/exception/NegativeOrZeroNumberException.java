package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeOrZeroNumberException extends RuntimeException {
    public NegativeOrZeroNumberException(Class clazz, String propertyName, Number propertyValue) {
        super(clazz.getSimpleName() + " " + propertyName + " cannot be negative or zero. Passed: " + propertyValue);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}