package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CaseFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,

        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "max_cpu_cooler_height")
        String maxCPUCoolerHeight,

        @JsonProperty(value = "max_gpu_length")
        String maxGPULength,

        @JsonProperty(value = "preinstalled_fans")
        String preInstalledFans,

        @JsonProperty(value = "tower_type")
        String towerType
) {
}