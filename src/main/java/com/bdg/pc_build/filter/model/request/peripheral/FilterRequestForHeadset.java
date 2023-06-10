package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForHeadset(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "frequency")
        String frequency,

        @JsonProperty(value = "connectivity")
        String connectivity,

        @JsonProperty(value = "cable_length")
        String cableLength
) {
}
