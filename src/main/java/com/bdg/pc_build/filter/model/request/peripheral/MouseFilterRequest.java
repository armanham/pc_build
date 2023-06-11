package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MouseFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "type")
        String type,

        @JsonProperty(value = "min_resolution")
        String minResolution,
        @JsonProperty(value = "max_resolution")
        String maxResolution,

        @JsonProperty(value = "min_cable_length")
        String minCableLength,
        @JsonProperty(value = "max_cable_length")
        String maxCableLength,

        @JsonProperty(value = "min_weight")
        String minWeight,
        @JsonProperty(value = "max_weight")
        String maxWeight
) {
}