package com.bdg.pc_build.exception;

public class ProductAlreadyCheckedInDesireLogException extends RuntimeException {

    public ProductAlreadyCheckedInDesireLogException(Long id) {
        super("Product with id: " + id + " is already checked in Desire Log");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
