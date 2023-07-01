package com.bdg.pc_build.order.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum OrderStatus {

    NEW, COMPLETED, CANCELED, IN_PROCESS;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(OrderStatus.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}