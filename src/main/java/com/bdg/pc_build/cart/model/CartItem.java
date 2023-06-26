package com.bdg.pc_build.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

public record CartItem(

        @Positive
        @JsonProperty("product_id")
        Long productId,

        @Positive
        @JsonProperty("quantity")
        Integer quantity
) {
}