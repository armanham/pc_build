package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MouseFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_max_resolution")
        String minMaxResolution,
        @JsonProperty(value = "max_max_resolution")
        String maxMaxResolution,

        @JsonProperty(value = "min_cable_length")
        String minCableLength,
        @JsonProperty(value = "max_cable_length")
        String maxCableLength,

        @JsonProperty(value = "min_weight")
        String minWeight,
        @JsonProperty(value = "max_weight")
        String maxWeight,

        @JsonProperty(value = "connectivity_types")
        List<String> connectivityTypes
) {
}