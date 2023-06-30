package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum PowerSourceType {

    AC, DC, BATTERY, SOLAR, WIND, HYDRO;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(PowerSourceType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}