package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record InternalHardDriveFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_capacity")
        String minCapacity,
        @JsonProperty(value = "max_capacity")
        String maxCapacity,

        @JsonProperty(value = "min_tdp")
        String minTdp,
        @JsonProperty(value = "max_tdp")
        String maxTdp,

        @JsonProperty(value = "internal_hard_drive_interface_types")
        List<String> internalHardDriveInterfaceTypes
) {
}