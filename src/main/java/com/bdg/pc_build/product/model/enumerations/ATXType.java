package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ATXType {

    mATX, ATX, eATX;

    public static List<String> toListOfStrings() {
        return Arrays.stream(ATXType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}