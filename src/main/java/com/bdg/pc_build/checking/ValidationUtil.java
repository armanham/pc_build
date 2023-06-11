package com.bdg.pc_build.checking;

import com.bdg.pc_build.checking.exception.NegativeOrZeroNumberException;
import com.bdg.pc_build.checking.exception.WrongCountException;
import com.bdg.pc_build.checking.exception.WrongPriceException;

public final class ValidationUtil {

    public static void validatePositivityOfNumber(Class clazz, String propertyName, Number number) {
        if (number.doubleValue() <= 0) {
            throw new NegativeOrZeroNumberException(clazz, propertyName, number);
        }
    }

    public static void validatePrices(final Double minPrice, final Double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new WrongPriceException(minPrice, maxPrice);
        }
    }

    public static void nullCheck(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}