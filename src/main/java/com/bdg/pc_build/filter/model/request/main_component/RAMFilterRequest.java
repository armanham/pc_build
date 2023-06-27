package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RAMFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,


        @JsonProperty(value = "min_count_of_ram")
        Integer minCountOfRam,
        @JsonProperty(value = "max_count_of_ram")
        Integer maxCountOfRam,

        @JsonProperty(value = "min_gb_of_ram")
        Integer minGbOfRam,
        @JsonProperty(value = "max_gb_of_ram")
        Integer maxGbOfRam,

        @JsonProperty(value = "min_tdp")
        Integer minTdp,
        @JsonProperty(value = "max_tdp")
        Integer maxTdp,

        @JsonProperty(value = "ddr_types")
        List<String> ddrTypes
) {
}