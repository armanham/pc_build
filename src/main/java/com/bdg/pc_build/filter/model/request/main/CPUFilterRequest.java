package com.bdg.pc_build.filter.model.request.main;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CPUFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_core_count")
        Integer minCoreCount,
        @JsonProperty(value = "max_core_count")
        Integer maxCoreCount,

        @JsonProperty(value = "min_core_clock")
        Double minCoreClock,
        @JsonProperty(value = "max_core_clock")
        Double maxCoreClock,

        @JsonProperty(value = "min_boost_clock")
        Double minBoostClock,
        @JsonProperty(value = "max_boost_clock")
        Double maxBoostClock,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

        @JsonProperty(value = "socket_types")
        List<String> socketTypes,

        @JsonProperty(value = "integrated_graphics")
        List<String> integratedGraphics
) {
}