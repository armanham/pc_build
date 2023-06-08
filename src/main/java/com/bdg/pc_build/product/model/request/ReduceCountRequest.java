package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReduceCountRequest(
        @JsonProperty(value = "product_name")
        String productName,

        @JsonProperty(value = "count_tobe_reduced")
        Integer countToBeReduced
) {
}