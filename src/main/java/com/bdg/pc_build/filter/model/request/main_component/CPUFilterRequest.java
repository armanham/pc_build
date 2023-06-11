package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CPUFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "core_count")
        String coreCount,

        @JsonProperty(value = "core_clock")
        String coreClock,

        @JsonProperty(value = "boost_clock")
        String boostClock,

        @JsonProperty(value = "tdp")
        String tdp,

        @JsonProperty(value = "integrated_graphics")
        String integratedGraphics,

        @JsonProperty(value = "socket_type")
        String socketType
) {
}