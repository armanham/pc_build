package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReduceCountRequest(
        @JsonProperty(value = "product_id")
        Long productId,

        @JsonProperty(value = "count_tobe_reduced")
        Integer countToBeReduced
) {
        public ReduceCountRequest {
                //TODO VALIDATIONS
        }
}