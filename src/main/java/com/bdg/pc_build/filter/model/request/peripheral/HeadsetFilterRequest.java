package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record HeadsetFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_frequency")
        String minFrequency,
        @JsonProperty(value = "max_frequency")
        String maxFrequency,

        @JsonProperty(value = "min_cable_length")
        String minCableLength,
        @JsonProperty(value = "max_cable_length")
        String maxCableLength,

        @JsonProperty(value = "connectivity_types")
        List<String> connectivityTypes
)
{
}