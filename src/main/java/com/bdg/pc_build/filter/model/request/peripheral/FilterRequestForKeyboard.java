package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForKeyboard(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "keyboard_class")
        String keyboardClass,

        @JsonProperty(value = "cable_length")
        String cableLength,

        @JsonProperty(value = "dimensino")
        String dimension,

        @JsonProperty(value = "weight")
        String weight
) {
}