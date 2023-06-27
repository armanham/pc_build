package com.bdg.pc_build.exception;

import com.bdg.pc_build.user.model.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class ProductAlreadyReportedInDesireLogException extends RuntimeException {
    public ProductAlreadyReportedInDesireLogException(User user) {
        super("The product has already been reported in Desire Log by " + user.getUsername() + ": ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
