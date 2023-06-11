package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InternalHardDriveFilterRequest(

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "type")
        String type,

        @JsonProperty(value = "min_capacity")
        String minCapacity,
        @JsonProperty(value = "max_capacity")
        String maxCapacity,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp
) {
}