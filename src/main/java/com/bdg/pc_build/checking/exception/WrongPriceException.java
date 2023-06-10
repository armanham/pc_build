package com.bdg.pc_build.checking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPriceException extends RuntimeException {

    public WrongPriceException(Class clazz, Double minPrice, Double maxPrice) {
        super(clazz.getSimpleName() + " minPrice: " + minPrice + ", maxPrice: " + maxPrice + ", not compatible prices: ");
    }

    public WrongPriceException(Class clazz, Double price) {
        super(clazz.getSimpleName() + " price: " + price + " invalid price: ");
    }

    public WrongPriceException(Double minPrice, Double maxPrice) {
        super("minPrice: " + minPrice + ", maxPrice: " + maxPrice + ", not compatible prices: ");
    }

    public WrongPriceException(Double price) {
        super("price: " + price + " invalid price: ");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}