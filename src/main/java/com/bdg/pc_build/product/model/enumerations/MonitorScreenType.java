package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MonitorScreenType {

    IPS, NanoIPS, VA, TN, LED;

    public static List<String> toListOfStrings() {
        return Arrays.stream(MonitorScreenType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}