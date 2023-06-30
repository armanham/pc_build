package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum MonitorScreenType {

    IPS, NANO_IPS, VA, TN, LED;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(MonitorScreenType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}