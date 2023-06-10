package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BetweenPricesRequest(
        @JsonProperty(value = "min_price", defaultValue = "0")
        String minPrice,
        @JsonProperty(value = "max_price", defaultValue = "1000000")
        String maxPrice
) {
    public BetweenPricesRequest {
//TODO VALIDATIONS
    }
}