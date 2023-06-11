package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record HeadsetFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_frequency")
        String minFrequency,
        @JsonProperty(value = "max_frequency")
        String maxFrequency,

        @JsonProperty(value = "connectivity")
        String connectivity,

        @JsonProperty(value = "min_cable_length")
        String minCableLength,
        @JsonProperty(value = "max_cable_length")
        String maxCableLength
) {
}