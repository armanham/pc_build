package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NotEnoughInStockException extends RuntimeException {

    public NotEnoughInStockException(Class clazz, String name, Integer count) {
        super("The product " + name + " of type " + clazz.getSimpleName()
                + "does not have enough stock. Current stock count: " + count);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}