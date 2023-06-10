package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongCountException extends RuntimeException {

    public WrongCountException(Integer count) {
        super("count: " + count + " invalid count: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}