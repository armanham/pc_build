package com.bdg.pc_build.product.service;

import com.bdg.pc_build.product.model.entity.Product;

public class ApranqyQichAException extends RuntimeException {
    public ApranqyQichAException(Class<? extends Product> aClass, String name, Integer count) {
    }
}

