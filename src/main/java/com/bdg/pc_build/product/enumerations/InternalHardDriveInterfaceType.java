package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum InternalHardDriveInterfaceType {

    SSD_M2, SSD, HDD;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(InternalHardDriveInterfaceType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}