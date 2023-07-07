package com.bdg.pc_build.util;

import com.bdg.pc_build.exception.NotCompatibleMinMaxValues;
import org.springframework.http.HttpStatus;

public final class ValidationUtil {

    public static void validateNonNegativeMinMaxValues(double minValue, double maxValue) {
        if (minValue < 0 || maxValue < 0 || minValue > maxValue) {
            throw new NotCompatibleMinMaxValues(HttpStatus.BAD_REQUEST, minValue, maxValue);
        }
    }
}