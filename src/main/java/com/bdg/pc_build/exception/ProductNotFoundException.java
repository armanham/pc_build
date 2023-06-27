package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Class clazz, String name) {
        super(clazz.getSimpleName() + "The product with name : " + name + " was not found: ");
    }

    public ProductNotFoundException(Class clazz, Long id) {
        super("Product of type: " + clazz.getSimpleName() + " with id: " + id + " not found: ");
    }

    public ProductNotFoundException(Long id) {
        super("The product with id : " + id + " was not found: ");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}