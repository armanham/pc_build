package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PowerSourceType {

    AC, DC, BATTERY, SOLAR, WIND, HYDRO;

    public static List<String> toListOfStrings() {
        return Arrays.stream(PowerSourceType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
