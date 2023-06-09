package com.bdg.pc_build.filter.model.request.peripheral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExternalHardDriveFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_capacity")
        Integer minCapacity,
        @JsonProperty(value = "max_capacity")
        Integer maxCapacity,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp
) {
}