package com.bdg.pc_build.exception;

public class NotCompatibleMinMaxValues extends RuntimeException {

    public NotCompatibleMinMaxValues(double minValue, double maxValue) {
       super("The minimum value " + minValue + " and/or maximum value " + maxValue +
               " are/is not compatible. Minimum value should be non-negative and less than or equal to the maximum value.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
