package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ApranqyQichAException extends RuntimeException {

    public ApranqyQichAException(Class clazz, String name, Integer maxCount) {
        super(clazz.getSimpleName() + " Count of product with name : " + name + " is: " + maxCount);
    }
}