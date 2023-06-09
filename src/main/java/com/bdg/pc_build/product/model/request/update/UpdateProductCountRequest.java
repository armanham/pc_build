package com.bdg.pc_build.product.model.request.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record UpdateProductCountRequest(

        @Positive(message = "product_id can not be negative or zero")
        @JsonProperty(value = "product_id")
        Long productId,

        @Min(value = 0, message = "count_tobe_changed can not be negative")
        @JsonProperty(value = "count_tobe_changed")
        Integer countToBeChanged
) {
}