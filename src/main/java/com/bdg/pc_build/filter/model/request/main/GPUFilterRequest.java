package com.bdg.pc_build.filter.model.request.main;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GPUFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_memory")
        Integer minMemory,
        @JsonProperty(value = "max_memory")
        Integer maxMemory,

        @JsonProperty(value = "min_core_clock")
        Double minCoreClock,
        @JsonProperty(value = "max_core_clock")
        Double maxCoreClock,

        @JsonProperty(value = "min_boost_clock")
        Double minBoostClock,
        @JsonProperty(value = "max_boost_clock")
        Double maxBoostClock,

        @JsonProperty(value = "min_length")
        Double minLength,
        @JsonProperty(value = "max_length")
        Double maxLength,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

        @JsonProperty(value = "gpu_interface_types")
        List<String> gpuInterfaceTypes
) {
}