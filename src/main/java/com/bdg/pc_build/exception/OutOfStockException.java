package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OutOfStockException extends ResponseStatusException {

    public OutOfStockException(HttpStatus httpStatus, Class clazz, String name) {
        super(httpStatus, "The product " + name + " of type " + clazz.getSimpleName()
                + "is out of stock");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}