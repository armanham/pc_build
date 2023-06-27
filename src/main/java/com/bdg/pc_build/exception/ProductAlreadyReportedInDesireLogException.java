package com.bdg.pc_build.exception;

import com.bdg.pc_build.user.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductAlreadyReportedInDesireLogException extends ResponseStatusException {
    public ProductAlreadyReportedInDesireLogException(User user) {
        super(HttpStatus.ALREADY_REPORTED, "The product has already been reported in Desire Log by " + user.getUsername() + ": ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
