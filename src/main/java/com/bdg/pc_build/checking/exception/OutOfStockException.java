package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String name, Integer maxCount) {
        super("Count of product with name : " + name + " is: " + maxCount);
    }
}