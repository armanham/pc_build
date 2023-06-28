package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

public record EditPriceRequest(

        @Positive
        @JsonProperty(value = "product_id")
        Long productId,

        @Positive
        @JsonProperty(value = "new_price")
        Double newPrice
) {
}