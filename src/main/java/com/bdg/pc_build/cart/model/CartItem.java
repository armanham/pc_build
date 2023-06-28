package com.bdg.pc_build.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

public record CartItem(

        @Positive(message = "product_id can not be negative or zero")
        @JsonProperty("product_id")
        Long productId,

        @Positive(message = "quantity can not be negative or zero")
        @JsonProperty("quantity")
        Integer quantity
) {
}