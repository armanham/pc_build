package com.bdg.pc_build.filter.validaton;

import com.bdg.pc_build.exception.NotCompatibleMinMaxValues;

public final class ValidationUtil {

    public static void validateNonNegativeMinMaxValues(double minValue, double maxValue) {
        if (minValue < 0 || maxValue < 0 || minValue > maxValue) {
            throw new NotCompatibleMinMaxValues(minValue, maxValue);
        }
    }
}