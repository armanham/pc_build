package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EfficiencyRating {

    NOPE, BRONZE, SILVER, GOLD, PLATINUM, TITANIUM;

    public static List<String> toListOfStrings() {
        return Arrays.stream(EfficiencyRating.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}