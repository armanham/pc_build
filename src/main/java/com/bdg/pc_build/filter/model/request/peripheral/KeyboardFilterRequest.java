package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record KeyboardFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_cable_length")
        Double minCableLength,
        @JsonProperty(value = "max_cable_length")
        Double maxCableLength,

        @JsonProperty(value = "min_weight")
        Double minWeight,
        @JsonProperty(value = "max_weight")
        Double maxWeight,

        @JsonProperty(value = "dimensions")
        List<String> dimensions,

        @JsonProperty(value = "connectivity_types")
        List<String> connectivityTypes
) {
}