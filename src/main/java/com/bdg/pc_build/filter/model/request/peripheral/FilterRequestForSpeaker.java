package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForSpeaker(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "frequency")
        String frequency,

        @JsonProperty(value = "power_source")
        String powerSource,

        @JsonProperty(value = "cable_length")
        String cableLength,

        @JsonProperty(value = "dimension")
        String dimension
) {
}
