package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum EfficiencyRating {

    NOPE, BRONZE, SILVER, GOLD, PLATINUM, TITANIUM;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(EfficiencyRating.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}