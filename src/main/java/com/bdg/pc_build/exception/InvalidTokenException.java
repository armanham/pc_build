package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("Provided token is not valid: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
