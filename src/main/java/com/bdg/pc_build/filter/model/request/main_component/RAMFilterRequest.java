package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RAMFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "speed")
        String speed,

        @JsonProperty(value = "count_of_RAM")
        String countOfRAM,

        @JsonProperty(value = "GB_of_RAM")
        String GBOfRAM,

        @JsonProperty(value = "tdp")
        String tdp
) {
}