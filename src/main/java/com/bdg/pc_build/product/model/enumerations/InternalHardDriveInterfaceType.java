package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum InternalHardDriveInterfaceType {

    SSD_M2, SSD, HDD;

    public static List<String> toListOfStrings() {
        return Arrays.stream(InternalHardDriveInterfaceType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}