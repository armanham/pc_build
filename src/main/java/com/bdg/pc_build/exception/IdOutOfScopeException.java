package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class IdOutOfScopeException extends RuntimeException{

    public IdOutOfScopeException(Class aClass, Long id) {
        super("The specified ID " + id + " is out of scope for product of type " + aClass.getSimpleName() + ": ");
    }

    public IdOutOfScopeException(Long id){
        super("The specified ID " + id + " is not in a valid range: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
