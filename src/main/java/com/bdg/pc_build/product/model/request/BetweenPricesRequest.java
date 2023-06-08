package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BetweenPricesRequest(
        @JsonProperty(value = "min_price")
        Double minPrice,

        @JsonProperty(value = "max_price")
        Double maxPrice) {
}