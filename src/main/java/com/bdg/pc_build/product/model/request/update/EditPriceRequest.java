package com.bdg.pc_build.product.model.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record EditPriceRequest(

        @Positive(message = "product_id can not be negative or zero")
        @JsonProperty(value = "product_id")
        Long productId,

        @Min(value = 0, message = "new_price can not be negative")
        @JsonProperty(value = "new_price")
        Double newPrice
) {
}