package com.bdg.pc_build.filter.model.request.main;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PowerSupplyFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,


        @JsonProperty(value = "min_wattage")
        Integer minWattage,
        @JsonProperty(value = "max_wattage")
        Integer maxWattage,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

        @JsonProperty(value = "modular_types")
        List<String> modularTypes,

        @JsonProperty(value = "efficiency_ratings")
        List<String> efficiencyRatings
) {
}