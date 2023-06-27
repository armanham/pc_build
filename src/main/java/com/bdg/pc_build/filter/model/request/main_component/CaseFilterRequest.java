package com.bdg.pc_build.filter.model.request.main_component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CaseFilterRequest(
        @JsonProperty("name")
        String name,

        @JsonProperty(value = "min_price")
        Double minPrice,
        @JsonProperty(value = "max_price")
        Double maxPrice,

        @JsonProperty(value = "min_cpu_cooler_height")
        Double minCpuCoolerHeight,
        @JsonProperty(value = "max_cpu_cooler_height")
        Double maxCpuCoolerHeight,

        @JsonProperty(value = "min_gpu_length")
        Double minGpuLength,
        @JsonProperty(value = "max_gpu_length")
        Double maxGpuLength,

        @JsonProperty(value = "min_pre_installed_fans")
        Integer minPreInstalledFans,
        @JsonProperty(value = "max_pre_installed_fans")
        Integer maxPreInstalledFans,

        @JsonProperty(value = "tower_types")
        List<String> towerTypes
) {
}