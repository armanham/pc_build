package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(Class clazz, String name) {
        super("The product " + name + " of type " + clazz.getSimpleName()
                + "is out of stock");
    }
}