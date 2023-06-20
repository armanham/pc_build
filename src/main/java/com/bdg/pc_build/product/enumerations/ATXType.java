package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ATXType {

    M_ATX, ATX, E_ATX;

    public static List<String> toListOfStrings() {
        return Arrays.stream(ATXType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}