package com.bdg.pc_build.exception;

public class ProductAlreadyCheckedException extends RuntimeException {

    public ProductAlreadyCheckedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
