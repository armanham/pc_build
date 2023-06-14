package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DDRType {

    DDR2,
    DDR3,
    DDR4,
    DDR5;

    public static List<String> toListOfStrings() {
        return Arrays.stream(DDRType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}