package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

import static com.bdg.pc_build.checking.pattern.Pattern.FLOATING_POINT_NUMBER_PATTERN;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE;

public record BetweenPricesRequest(

        @Pattern(
                regexp = FLOATING_POINT_NUMBER_PATTERN,
                message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
        )
        @JsonProperty(value = "min_price")
        String minPrice,

        @Pattern(
                regexp = FLOATING_POINT_NUMBER_PATTERN,
                message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
        )
        @JsonProperty(value = "max_price")
        String maxPrice
) {
    public BetweenPricesRequest {
    }
}