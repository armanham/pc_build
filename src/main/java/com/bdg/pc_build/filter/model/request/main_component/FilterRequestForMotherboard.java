package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForMotherboard(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "socket_type")
        String socketType,

        @JsonProperty(value = "atx_type")
        String atxType,

        @JsonProperty(value = "memory_max")
        String memoryMax,

        @JsonProperty(value = "memory_slots")
        String memorySlots,

        @JsonProperty(value = "memory_type")
        String memoryType,

        @JsonProperty(value = "internal_connections")
        String internalConnections,

        @JsonProperty(value = "gpu_interface_type")
        String gpuInterfaceType,

        @JsonProperty(value = "tdp")
        String tdp
) {
}