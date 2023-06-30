package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum ConnectivityType {

    WIRED, WIRELESS, USB, BLUETOOTH;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(ConnectivityType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}