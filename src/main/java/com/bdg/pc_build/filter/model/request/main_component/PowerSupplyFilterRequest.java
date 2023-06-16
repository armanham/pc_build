package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PowerSupplyFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,


        @JsonProperty(value = "min_wattage")
        String minWattage,
        @JsonProperty(value = "max_wattage")
        String maxWattage,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp,

        @JsonProperty(value = "modular_types")
        List<String> modularTypes,

        @JsonProperty(value = "efficiency_ratings")
        List<String> efficiencyRatings
) {
}