package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NotCompatibleException extends RuntimeException{

    public NotCompatibleException(String message) {
        super(message);
    }
}