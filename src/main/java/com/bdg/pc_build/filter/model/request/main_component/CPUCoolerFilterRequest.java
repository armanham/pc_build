package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CPUCoolerFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_fan_rpm")
        String minFanRpm,
        @JsonProperty(value = "max_fan_rpm")
        String maxFanRpm,

        @JsonProperty(value = "socket_type")
        String socketType,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}