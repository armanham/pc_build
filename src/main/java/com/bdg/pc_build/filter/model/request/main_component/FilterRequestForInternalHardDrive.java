package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForInternalHardDrive(

        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "type")
        String type,

        @JsonProperty(value = "capacity")
        String capacity,

        @JsonProperty(value = "tdp")
        String tdp
) {
}
