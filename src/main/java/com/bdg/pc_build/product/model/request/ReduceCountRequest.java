package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;

public record ReduceCountRequest(

        @Positive
        @JsonProperty(value = "product_id")
        Long productId,

        @Positive
        @JsonProperty(value = "count_tobe_reduced")
        Integer countToBeReduced
) {
}