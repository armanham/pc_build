package com.bdg.pc_build.util;

public final class Pattern {

    private static final String COMMON_MESSAGE = "must look like one of those: \n";

    public static final String BOOLEAN_PATTERN = "\\s*(?i:true|false)\\s*";

    public static final String WRONG_BOOLEAN_PATTERN = "field must be either 'true' or 'false'";


    //Valid Patterns for Enumerations
    public static final String ATX_TYPE_ENUM_PATTERN = "\\s*(?i:M_ATX|ATX|E_ATX)\\s*";
    public static final String CONNECTIVITY_TYPE_ENUM_PATTERN = "\\s*(?i:WIRED|WIRELESS|USB|BLUETOOTH)\\s*";
    public static final String DDR_TYPE_ENUM_PATTERN = "\\s*(?i:DDR2|DDR3|DDR4|DDR5)\\s*";
    public static final String EFFICIENCY_TYPE_ENUM_PATTERN = "\\s*(?i:NOPE|BRONZE|SILVER|GOLD|PLATINUM|TITANIUM)\\s*";
    public static final String GPU_INTERFACE_TYPE_ENUM_PATTERN = "\\s*(?i:PCIE_4_0_X16|PCIE_3_0_X16|PCIE_X8)\\s*";
    public static final String INTERNAL_HARD_DRIVE_INTERFACE_TYPE_ENUM_PATTERN = "\\s*(?i:SSD_M2|SSD|HDD)\\s*";
    public static final String MONITOR_SCREEN_TYPE_ENUM_PATTERN = "\\s*(?i:IPS|NANO_IPS|VA|TN|LED)\\s*";
    public static final String POWER_SOURCE_TYPE_ENUM_PATTERN = "\\s*(?i:AC|DC|BATTERY|SOLAR|WIND|HYDRO)\\s*";
    public static final String SOCKET_TYPE_ENUM_PATTERN =
            "\\s*(?i:AM1|AM2PLUS|AM3|AM3PLUS|AM4|AM5|FM1|FM2" +
                    "|FM2PLUS|G34|LGA771|LGA775|LGA1150|LGA1151" +
                    "|LGA1155|LGA1156|LGA1200|LGA1356|LGA1366" +
                    "|LGA1700|LGA2011|LGA2011MINUS3|LGA2066|STR4|STR_X4)\\s*";
    public static final String TOWER_TYPE_ENUM_PATTERN = "\\s*(?i:FULL|MID|MINI)\\s*";
    public static final String MODULAR_TYPE_ENUM_PATTERN = "\\s*(?i:FULL|NO|SEMI)\\s*";

    public static final String WRONG_ENUM_PATTERN_COMMON_MESSAGE = "Invalid enum pattern: ";
}