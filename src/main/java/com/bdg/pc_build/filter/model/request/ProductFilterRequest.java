package com.bdg.pc_build.filter.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductFilterRequest(
        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice
) {
}