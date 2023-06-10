package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FilterRequestForCase(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "max_CPU_cooler_height")
        String maxCPUCoolerHeight,

        @JsonProperty(value = "max_GPU_length")
        String maxGPULength,

        @JsonProperty(value = "preinstalled_fans")
        String preInstalledFans,

        @JsonProperty(value = "tower_type")
        String towerType
) {
}