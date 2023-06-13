package com.bdg.pc_build.checking.pattern;

public final class Pattern {

    private static final String COMMON_MESSAGE = "must look like one of those: \n";

    public static final String FLOATING_POINT_NUMBER_PATTERN = "^\\s*[0-9]+(?:\\.[0-9]+)?\\s*$";

    public static final String WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE =
            "field " + COMMON_MESSAGE +
                    "\"123\"\n" +
                    "\"0.45\"\n" +
                    "\"987654321.0\"";

    public static final String POSITIVE_INTEGER_NUMBER_PATTERN = "^\\s*0|[1-9]\\d*\\s*$";

    public static final String WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE =
            "field must be a non negative integer number: ";
}