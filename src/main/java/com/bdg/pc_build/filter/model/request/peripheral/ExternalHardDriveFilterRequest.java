package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExternalHardDriveFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "capacity")
        String capacity,

        @JsonProperty(value = "tdp")
        String tdp
) {
}