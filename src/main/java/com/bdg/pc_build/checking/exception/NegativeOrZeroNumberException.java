package com.bdg.pc_build.checking.exception;

public class NegativeOrZeroNumberException extends RuntimeException {
    public NegativeOrZeroNumberException(Class clazz, String propertyName, Number number) {
        super(clazz.getSimpleName() + " " + propertyName + " cannot be negative or zero. Passed: " + number);
    }
}