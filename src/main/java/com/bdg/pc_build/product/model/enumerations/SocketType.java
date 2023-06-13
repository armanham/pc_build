package com.bdg.pc_build.product.model.enumerations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum SocketType {
    AM1, AM2Plus, AM3, AM3Plus, AM4, AM5, FM1, FM2,
    FM2Plus, G34, LGA771, LGA775, LGA1150, LGA1151,
    LGA1155, LGA1156, LGA1200, LGA1356, LGA1366,
    LGA1700, LGA2011, LGA2011Minus3, LGA2066, sTR4, sTRX4;

    public static List<String> toListOfStrings() {
        return Arrays.stream(SocketType.values())
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}