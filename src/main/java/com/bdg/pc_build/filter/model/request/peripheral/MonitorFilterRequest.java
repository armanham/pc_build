package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record MonitorFilterRequest(
        @JsonProperty("name")
        String name,

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

        @JsonProperty(value = "screen_types")
        List<String> screenTypes
) {
}