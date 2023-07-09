package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class OrderStatusIllegalStateException extends ResponseStatusException {

    public OrderStatusIllegalStateException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
