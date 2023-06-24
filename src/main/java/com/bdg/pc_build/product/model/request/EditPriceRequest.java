package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EditPriceRequest(

        @JsonProperty(value = "product_id")
        Long productId,

        @JsonProperty(value = "new_price")
        Double newPrice
) {
        //todo validations
}