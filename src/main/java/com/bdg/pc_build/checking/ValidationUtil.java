package com.bdg.pc_build.checking;

import com.bdg.pc_build.checking.exception.BlankOrEmptyStringException;
import com.bdg.pc_build.checking.exception.NegativeOrZeroNumberException;
import com.bdg.pc_build.checking.exception.WrongPriceException;

public final class ValidationUtil {

    public static void validatePositivityOfNumber(Class clazz, String propertyName, Number number) {
        nullCheck(clazz, propertyName, number);
        if (number.doubleValue() <= 0) {
            throw new NegativeOrZeroNumberException(clazz, propertyName, number);
        }
    }

    public static void validateNonBlankAndNonNullString(Class clazz, String propertyName, String str) {
        nullCheck(clazz, propertyName, str);
        if (str.isBlank() || str.isEmpty()) {
            throw new BlankOrEmptyStringException(clazz, propertyName, str);
        }
    }

    public static void validatePrices(final Double minPrice, final Double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new WrongPriceException(minPrice, maxPrice);
        }
    }

    public static void nullCheck(Class clazz, String propertyName, Object o) {
        if (o == null) {
            throw new NullPointerException(clazz.getSimpleName() + " " + propertyName + " cannot be null. Passed: ");
        }
    }
}