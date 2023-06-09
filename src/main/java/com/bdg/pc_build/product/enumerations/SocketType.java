package com.bdg.pc_build.product.enumerations;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum SocketType {

    AM1, AM2PLUS, AM3, AM3PLUS, AM4, AM5, FM1, FM2,
    FM2PLUS, G34, LGA771, LGA775, LGA1150, LGA1151,
    LGA1155, LGA1156, LGA1200, LGA1356, LGA1366,
    LGA1700, LGA2011, LGA2011MINUS3, LGA2066, STR4, STR_X4;

    public static Set<String> toSetOfStrings() {
        return Arrays.stream(SocketType.values())
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }
}