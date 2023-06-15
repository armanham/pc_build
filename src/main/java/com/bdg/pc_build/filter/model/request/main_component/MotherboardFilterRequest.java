package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MotherboardFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_memory")
        String minMemory,
        @JsonProperty(value = "max_memory")
        String maxMemory,

        @JsonProperty(value = "min_memory_slots")
        String minMemorySlots,
        @JsonProperty(value = "max_memory_slots")
        String maxMemorySlots,


        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp,

        @JsonProperty(value = "socket_types")
        List<String> socketTypes,

        @JsonProperty(value = "atx_types")
        List<String> atxTypes,

        @JsonProperty(value = "gpu_interface_types")
        List<String> gpuInterfaceTypes,

        @JsonProperty(value = "ddr_types")
        List<String> ddrTypes,

        @JsonProperty(value = "is_m2")
        List<String> isM2
) {
}