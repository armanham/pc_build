package com.bdg.pc_build.filter.model.request.main;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CPUCoolerFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_fan_rpm")
        Integer minFanRpm,
        @JsonProperty(value = "max_fan_rpm")
        Integer maxFanRpm,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

        @JsonProperty(value = "socket_types")
        List<String> socketTypes
) {
}