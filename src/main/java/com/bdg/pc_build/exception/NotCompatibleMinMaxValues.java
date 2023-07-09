package com.bdg.pc_build.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotCompatibleMinMaxValues extends ResponseStatusException {

    public NotCompatibleMinMaxValues(HttpStatus httpStatus, double minValue, double maxValue) {
        super(httpStatus, "The minimum value " + minValue + " and/or maximum value " + maxValue +
                " are/is not compatible. Minimum value should be non-negative and less than or equal to the maximum value.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}