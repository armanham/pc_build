package com.bdg.pc_build.product.model.request;

import com.bdg.pc_build.product.model.enumerations.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRequest(

        @JsonProperty(value = "name")
        String name,

        @JsonProperty(value = "price")
        String price,

        @JsonProperty(value = "purchased_price")
        String purchasedPrice,

        @JsonProperty(value = "count")
        String count,

        @JsonProperty(value = "tdp")
        String tdp,
        @JsonProperty(value = "socket")
        String socket,

//Display

        //Monitor
        @JsonProperty(value = "screen_size")
        String screenSize,

        @JsonProperty(value = "refresh_rate")
        String refreshRate,

        @JsonProperty(value = "screen_type")
        String screenType,

//Main Components

        //Case
        @JsonProperty(value = "max_CPU_cooler_height")
        String maxCPUCoolerHeight,

        @JsonProperty(value = "max_GPU_length")
        String maxGPULength,

        @JsonProperty(value = "pre_installed_fans")
        String preInstalledFans,

        @JsonProperty(value = "is_ATX")
        String towerType,


        //CPUCooler
        @JsonProperty(value = "fan_RPM")
        String fanRPM,

        //CPU
        @JsonProperty(value = "core_count")
        String coreCount,
        @JsonProperty(value = "core_clock")
        String coreClock,
        @JsonProperty(value = "boost_clock")
        String boostClock,
        @JsonProperty(value = "integrated_graphics")
        String integratedGraphics,

        @JsonProperty(value = "socket_type")
        String socketType,

        //External Hard Drive
        @JsonProperty(value = "type")
        String type,
        @JsonProperty(value = "capacity")
        String capacity,


        //GPU
        @JsonProperty(value = "gpu_interface")
        String gpuInterfaceType,
        @JsonProperty(value = "memory")
        String memory,
        @JsonProperty(value = "length")
        String length,

        //Motherboard
        @JsonProperty(value = "memory_max")
        String memoryMax,
        @JsonProperty(value = "memory_slots")
        String memorySlots,
        @JsonProperty(value = "memory_type")
        String memoryType,
        @JsonProperty(value = "internal_connections")
        String internalConnections,
        @JsonProperty(value = "atx_type")
        String atxType,

        //PowerSupply
        @JsonProperty(value = "efficiency_rating")
        String efficiencyRating,
        @JsonProperty(value = "wattage")
        String wattage,
        @JsonProperty(value = "modular")
        String modular,

        //RAM
        @JsonProperty(value = "speed")
        String speed,
        @JsonProperty(value = "count_of_RAM")
        String countOfRAM,
        @JsonProperty(value = "GB_of_RAM")
        String GBOfRAM,


//peripheral

        @JsonProperty(value = "cable_length")
        String cableLength,

        //Headset
        @JsonProperty(value = "frequency")
        String frequency,
        @JsonProperty(value = "connectivity")
        String connectivity,

        //Mouse
        @JsonProperty(value = "max_resolution")
        String maxResolution,
        @JsonProperty(value = "weight")
        String weight,

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