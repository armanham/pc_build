package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForPowerSupply(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "efficiency_rating")
        String efficiencyRating,

        @JsonProperty(value = "wattage")
        String wattage,

        @JsonProperty(value = "modular")
        String modular,

        @JsonProperty(value = "tdp")
        String tdp
) {
}