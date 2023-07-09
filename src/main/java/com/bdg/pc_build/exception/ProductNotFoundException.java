package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {

    public ProductNotFoundException(HttpStatus httpStatus, Class clazz, Long id) {
        super(httpStatus, "Product of type: " + clazz.getSimpleName() + " with id: " + id + " not found: ");
    }

    public ProductNotFoundException(HttpStatus httpStatus, Long id) {
        super(httpStatus, "The product with id : " + id + " was not found: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}