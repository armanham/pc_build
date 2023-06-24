package com.bdg.pc_build.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CartItem(
        @JsonProperty("product_id")
        Long productId,

        @JsonProperty("quantity")
        Integer quantity
) {
}