package com.bdg.pc_build.checking;

public final class ValidationUtil {

    public static void validateNonNegativeMinMaxValues(double minValue, double maxValue) {
        if (minValue < 0 || maxValue < 0 || minValue > maxValue) {
            throw new IllegalArgumentException();
        }
    }
}