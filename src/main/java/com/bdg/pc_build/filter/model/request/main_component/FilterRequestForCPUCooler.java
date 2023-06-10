package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForCPUCooler(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "fan_RPM")
        String fanRPM,

        @JsonProperty(value = "socket_type")
        String socketType,

        @JsonProperty(value = "tdp")
        String tdp
) {
}