package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForMouse(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "type")
        String type,

        @JsonProperty(value = "max_resolution")
        String maxResolution,

        @JsonProperty(value = "cable_lenght")
        String cableLength,

        @JsonProperty(value = "weight")
        String weight
) {
}