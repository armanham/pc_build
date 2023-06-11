package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RAMFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_speed")
        String minSpeed,
        @JsonProperty(value = "max_speed")
        String maxSpeed,

        @JsonProperty(value = "min_count_of_ram")
        String minCountOfRam,
        @JsonProperty(value = "max_count_of_ram")
        String maxCountOfRam,

        @JsonProperty(value = "min_gb_of_ram")
        String minGbOfRam,
        @JsonProperty(value = "max_gb_of_ram")
        String maxGbOfRam,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}