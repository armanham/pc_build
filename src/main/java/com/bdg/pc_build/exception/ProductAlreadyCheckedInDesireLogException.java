package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductAlreadyCheckedInDesireLogException extends ResponseStatusException {

    public ProductAlreadyCheckedInDesireLogException(HttpStatus httpStatus, Long id) {
        super(httpStatus, "Product with id: " + id + " is already checked in Desire Log: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
