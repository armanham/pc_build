package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class NotCompletableException extends RuntimeException{

    public NotCompletableException(String message) {
        super(message);
    }
}