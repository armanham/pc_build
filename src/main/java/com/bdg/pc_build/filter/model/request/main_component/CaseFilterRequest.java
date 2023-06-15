package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CaseFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        String minPrice,
        @JsonProperty(value = "max_price")
        String maxPrice,

        @JsonProperty(value = "min_cpu_cooler_height")
        String minCpuCoolerHeight,
        @JsonProperty(value = "max_cpu_cooler_height")
        String maxCpuCoolerHeight,

        @JsonProperty(value = "min_gpu_length")
        String minGpuLength,
        @JsonProperty(value = "max_gpu_length")
        String maxGpuLength,

        @JsonProperty(value = "min_pre_installed_fans")
        String minPreInstalledFans,
        @JsonProperty(value = "max_pre_installed_fans")
        String maxPreInstalledFans,

        @JsonProperty(value = "tower_types")
        List<String> towerTypes
) {
}