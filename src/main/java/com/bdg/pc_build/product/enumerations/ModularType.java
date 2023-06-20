package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ModularType {

    FULL, NO, SEMI;

    public static List<String> toListOfStrings() {
        return Arrays.stream(ModularType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}