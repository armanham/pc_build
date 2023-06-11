package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CaseFilterRequest(
        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_cpu_cooler_height")
        String minCPUCoolerHeight,
        @JsonProperty(value = "max_cpu_cooler_height")
        String maxCPUCoolerHeight,

        @JsonProperty(value = "min_gpu_length")
        String minGPULength,
        @JsonProperty(value = "max_gpu_length")
        String maxGPULength,

        @JsonProperty(value = "min_pre_installed_fans")
        String minPreInstalledFans,
        @JsonProperty(value = "max_pre_installed_fans")
        String maxPreInstalledFans,

        @JsonProperty(value = "tower_type")
        String towerType
) {
}