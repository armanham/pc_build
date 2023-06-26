package com.bdg.pc_build.exception;

import com.bdg.pc_build.product.model.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NotEnoughInStockException extends RuntimeException {
    public NotEnoughInStockException(Class aClass, String name, Integer count) {
        super("The product " + name + " of type " + aClass.getSimpleName()
                + "does not have enough stock. Current stock count: " + count);
    }
}