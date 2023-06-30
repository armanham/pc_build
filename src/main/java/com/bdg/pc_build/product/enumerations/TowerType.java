package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum TowerType {

    FULL, MID, MINI;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(TowerType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}