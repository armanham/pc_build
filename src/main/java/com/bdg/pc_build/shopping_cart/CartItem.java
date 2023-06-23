package com.bdg.pc_build.shopping_cart;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CartItem(
        @JsonProperty("id")
        Long productId,

        @JsonProperty("quantity")
        Integer quantity
) {
}