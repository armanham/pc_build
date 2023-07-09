package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductAlreadyReportedInDesireLogException extends ResponseStatusException {

    public ProductAlreadyReportedInDesireLogException(HttpStatus httpStatus, String userEmail) {
        super(httpStatus, "The product has already been reported in Desire Log by " + userEmail + ": ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}