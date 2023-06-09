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

        @JsonProperty(value = "tdp")
        Integer tdp,
        @JsonProperty(value = "socket")
        String socket,

//Display

        //Monitor
        @JsonProperty(value = "screen_size")
        Double screenSize,

        @JsonProperty(value = "refresh_rate")
        Integer refreshRate,

        @JsonProperty(value = "screen_type")
        String screenType,

//Main Components

        //Case
        @JsonProperty(value = "max_CPU_cooler_height")
        Integer maxCPUCoolerHeight,

        @JsonProperty(value = "max_GPU_length")
        Double maxGPULength,

        @JsonProperty(value = "pre_installed_fans")
        Integer preInstalledFans,

        @JsonProperty(value = "is_ATX")
        Boolean isATX,


        //CPUCooler
        @JsonProperty(value = "fan_RPM")
        Integer fanRPM,

        //CPU
        @JsonProperty(value = "core_count")
        Integer coreCount,
        @JsonProperty(value = "core_clock")
        Double coreClock,
        @JsonProperty(value = "boost_clock")
        Double boostClock,
        @JsonProperty(value = "integrated_graphics")
        String integratedGraphics,

        //External Hard Drive
        @JsonProperty(value = "type")
        String type,
        @JsonProperty(value = "capacity")
        Integer capacity,

        //GPU
        @JsonProperty(value = "chipset")
        String chipset,
        @JsonProperty(value = "memory")
        Integer memory,
        @JsonProperty(value = "length")
        Double length,

        //Motherboard
        @JsonProperty(value = "socket_CPU")
        String socketCpu,
        @JsonProperty(value = "form_factor")
        String formFactor,
        @JsonProperty(value = "memory_max")
        Integer memoryMax,
        @JsonProperty(value = "memory_slots")
        Integer memorySlots,
        @JsonProperty(value = "memory_type")
        String memoryType,

        //PowerSupply
        @JsonProperty(value = "efficiency_rating")
        String efficiencyRating,
        @JsonProperty(value = "wattage")
        Integer wattage,
        @JsonProperty(value = "modular")
        Boolean modular,

        //RAM
        @JsonProperty(value = "speed")
        Integer speed,
        @JsonProperty(value = "count_of_RAM")
        Integer countOfRAM,
        @JsonProperty(value = "GB_of_RAM")
        Double GBOfRAM,


//peripheral

        @JsonProperty(value = "cable_length")
        Double cableLength,

        //Headset
        @JsonProperty(value = "frequency")
        Integer frequency,
        @JsonProperty(value = "connectivity")
        String connectivity,

        //Mouse
        @JsonProperty(value = "max_resolution")
        Integer maxResolution,
        @JsonProperty(value = "weight")
        Double weight,

        //Keyboard
        @JsonProperty(value = "keyboard_class")
        String keyboardClass,

        //Speaker
        @JsonProperty(value = "power_source")
        String powerSource,
        @JsonProperty(value = "dimension")
        String dimension
) {
}