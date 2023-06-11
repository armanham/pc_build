package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MotherboardFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "socket_type")
        String socketType,

        @JsonProperty(value = "atx_type")
        String atxType,

        @JsonProperty(value = "min_memory")
        String minMemory,
        @JsonProperty(value = "max_memory")
        String maxMemory,

        @JsonProperty(value = "min_memory_slots")
        String minMemorySlots,
        @JsonProperty(value = "max_memory_slots")
        String maxMemorySlots,

        @JsonProperty(value = "memory_type")
        String memoryType,

        @JsonProperty(value = "internal_connections")
        String internalConnections,

        @JsonProperty(value = "gpu_interface_type")
        String gpuInterfaceType,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}