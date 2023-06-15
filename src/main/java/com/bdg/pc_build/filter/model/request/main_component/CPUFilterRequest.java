package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CPUFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_core_count")
        String minCoreCount,
        @JsonProperty(value = "max_core_count")
        String maxCoreCount,

        @JsonProperty(value = "min_core_clock")
        String minCoreClock,
        @JsonProperty(value = "max_core_clock")
        String maxCoreClock,

        @JsonProperty(value = "min_boost_clock")
        String minBoostClock,
        @JsonProperty(value = "max_boost_clock")
        String maxBoostClock,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp,

        @JsonProperty(value = "socket_types")
        List<String> socketTypes,

        @JsonProperty(value = "integrated_graphics")
        List<String> integratedGraphics
) {
}