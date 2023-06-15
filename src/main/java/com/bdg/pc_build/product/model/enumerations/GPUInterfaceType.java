package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GPUInterfaceType {

    PCIE_4_0_X16, PCIE_3_0_X16, PCIE_X8;

    public static List<String> toListOfStrings() {
        return Arrays.stream(GPUInterfaceType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}