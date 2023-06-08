package com.bdg.pc_build.product.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRequest(

        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "price")
        Double price,

        @JsonProperty(value = "purchased_price")
        Double purchasedPrice,

        @JsonProperty(value = "count")
        Integer count,

        @JsonProperty(value = "screen_size")
        Double screenSize,

        @JsonProperty(value = "refresh_rate")
        Integer refreshRate,

        @JsonProperty(value = "screen_type")
        String screenType,

        @JsonProperty(value = "max_CPU_cooler_height")
        Integer maxCPUCoolerHeight,

        @JsonProperty(value = "max_GPU_length")
        Double maxGPULength,

        @JsonProperty(value = "pre_installed_fans")
        Integer preInstalledFans,

        @JsonProperty(value = "is_ATX")
        Boolean isATX,

        @JsonProperty(value = "tdp")
        Integer tdp,

        @JsonProperty(value = "fan_RPM")
        Integer fanRPM,

        @JsonProperty(value = "socket")
        String socket
) {
}