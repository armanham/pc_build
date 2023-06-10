package com.bdg.pc_build.checking;

import com.bdg.pc_build.checking.exception.WrongCountException;
import com.bdg.pc_build.checking.exception.WrongPriceException;

public final class ValidationUtil {

    public static void validatePrice(final Double price) {
        if (price < 0) {
            throw new WrongPriceException(price);
        }
    }

    public static void validatePrices(final Double minPrice, final Double maxPrice) {
        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new WrongPriceException(minPrice, maxPrice);
        }
    }

    public static void validateCount(final Integer count) {
        if (count < 0) {
            throw new WrongCountException(count);
        }
    }

    public static void nullCheck(final Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}