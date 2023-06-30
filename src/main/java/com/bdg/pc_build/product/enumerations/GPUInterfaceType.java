package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum GPUInterfaceType {

    PCIE_4_0_X16, PCIE_3_0_X16, PCIE_X8;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(GPUInterfaceType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}