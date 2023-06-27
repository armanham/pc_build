package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MotherboardFilterRequest(
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

        @JsonProperty(value = "min_memory_slots")
        Integer minMemorySlots,
        @JsonProperty(value = "max_memory_slots")
        Integer maxMemorySlots,


        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

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