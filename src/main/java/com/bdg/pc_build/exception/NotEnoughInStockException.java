package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotEnoughInStockException extends ResponseStatusException {

    public NotEnoughInStockException(Class clazz, String name, Integer count) {
        super(HttpStatus.OK, "The product " + name + " of type " + clazz.getSimpleName()
                + "does not have enough stock. Current stock count: " + count);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}