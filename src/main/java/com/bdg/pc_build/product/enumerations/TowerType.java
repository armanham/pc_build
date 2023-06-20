package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TowerType {

    FULL, MID, MINI;

    public static List<String> toListOfStrings() {
        return Arrays.stream(TowerType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}