package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotCompatibleException extends ResponseStatusException {

    public NotCompatibleException(String message) {
        super(HttpStatus.OK, message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}