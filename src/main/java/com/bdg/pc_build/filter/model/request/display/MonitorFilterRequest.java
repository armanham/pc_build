package com.bdg.pc_build.filter.model.request.display;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MonitorFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_screen_size")
        String minScreenSize,
        @JsonProperty(value = "max_screen_size")
        String maxScreenSize,

        @JsonProperty(value = "min_refresh_rate")
        String minRefreshRate,
        @JsonProperty(value = "max_refresh_rate")
        String maxRefreshRate,

        @JsonProperty(value = "screen_rate")
        String screenType
) {
}