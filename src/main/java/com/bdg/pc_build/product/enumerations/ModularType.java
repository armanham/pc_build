package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum ModularType {

    FULL, NO, SEMI;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(ModularType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}