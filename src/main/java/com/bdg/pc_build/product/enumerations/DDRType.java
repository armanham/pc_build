package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum DDRType {

    DDR2, DDR3, DDR4, DDR5;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(DDRType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}