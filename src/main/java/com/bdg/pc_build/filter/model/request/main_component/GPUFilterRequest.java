package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GPUFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "GPU_interface_type")
        String gpuInterfaceType,

        @JsonProperty(value = "min_memory")
        String minMemory,
        @JsonProperty(value = "max_memory")
        String maxMemory,

        @JsonProperty(value = "min_core_clock")
        String minCoreClock,
        @JsonProperty(value = "max_core_clock")
        String maxCoreClock,

        @JsonProperty(value = "min_boost_clock")
        String minBoostClock,
        @JsonProperty(value = "max_boost_clock")
        String maxBoostClock,

        @JsonProperty(value = "min_length")
        String minLength,
        @JsonProperty(value = "max_length")
        String maxLength,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}