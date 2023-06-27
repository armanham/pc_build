package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SpeakerFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_frequency")
        Integer minFrequency,
        @JsonProperty(value = "max_frequency")
        Integer maxFrequency,

        @JsonProperty(value = "min_cable_length")
        Double minCableLength,
        @JsonProperty(value = "max_cable_length")
        Double maxCableLength,

        @JsonProperty(value = "dimensions")
        List<String> dimensions,

        @JsonProperty(value = "power_source_types")
        List<String> powerSourceTypes
) {
}