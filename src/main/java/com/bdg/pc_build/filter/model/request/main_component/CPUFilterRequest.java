package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CPUFilterRequest(
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

        @JsonProperty(value = "integrated_graphics")
        String integratedGraphics,

        @JsonProperty(value = "socket_type")
        String socketType,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}