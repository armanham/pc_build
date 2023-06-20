package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ConnectivityType {

    WIRED, WIRELESS, USB, BLUETOOTH;

    public static List<String> toListOfStrings() {
        return Arrays.stream(ConnectivityType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}