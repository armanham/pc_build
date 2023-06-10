package com.bdg.pc_build.filter.model.request.display;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForMonitor(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "screen_size")
        String screenSize,

        @JsonProperty(value = "refresh_rate")
        String refreshRate,

        @JsonProperty(value = "screen_rate")
        String screenType
) {
}
