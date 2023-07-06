package com.bdg.pc_build.filter.model.request.main;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CoolerFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp
) {
}