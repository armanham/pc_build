package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PowerSupplyFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "efficiency_rating")
        String efficiencyRating,

        @JsonProperty(value = "min_wattage")
        String minWattage,
        @JsonProperty(value = "max_wattage")
        String maxWattage,

        @JsonProperty(value = "modular")
        String modular,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}