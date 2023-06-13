package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum GPUInterfaceType {

    PCIe_4_0_x16, PCIe_3_0_x16, PCIe_x8;

    public static List<String> toListOfStrings() {
        return Arrays.stream(GPUInterfaceType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}