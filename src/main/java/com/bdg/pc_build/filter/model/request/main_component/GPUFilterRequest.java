package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GPUFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "GPU_interface_type")
        String  gpuInterfaceType,

        @JsonProperty(value = "memory")
        String  memory,

        @JsonProperty(value = "core_clock")
        String coreClock,

        @JsonProperty(value = "boost_clock")
        String boostClock,

        @JsonProperty(value = "length")
        String length,

        @JsonProperty(value = "tdp")
        String  tdp
) {
}
