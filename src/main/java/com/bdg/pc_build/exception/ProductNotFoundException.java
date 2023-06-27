package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {

    public ProductNotFoundException(Class clazz, Long id) {
        super(HttpStatus.NOT_FOUND, "Product of type: " + clazz.getSimpleName() + " with id: " + id + " not found: ");
    }

    public ProductNotFoundException(Long id) {
        super(HttpStatus.NOT_FOUND, "The product with id : " + id + " was not found: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}