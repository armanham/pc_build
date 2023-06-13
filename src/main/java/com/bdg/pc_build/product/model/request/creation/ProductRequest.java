package com.bdg.pc_build.product.model.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

//@Valid
public record ProductRequest(

        @NotBlank(message = "'name' field can not be blank")
        @JsonProperty(value = "name", required = true)
        String name,

        @NotBlank(message = "'price' field can not be blank")
        @Pattern(
                regexp = FLOATING_POINT_NUMBER_PATTERN,
                message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
        )
        @JsonProperty(value = "price", required = true)
        String price,

        @NotBlank(message = "'purchased_price' field can not be blank")
        @Pattern(
                regexp = FLOATING_POINT_NUMBER_PATTERN,
                message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
        )
        @JsonProperty(value = "purchased_price", required = true)
        String purchasedPrice,

        @NotBlank(message = "'count' field can not be blank")
        @Pattern(
                regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
                message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
        )
        @JsonProperty(value = "count", required = true)
        String count,

        @JsonProperty(value = "tdp")
        String tdp,
        @JsonProperty(value = "socket_type")
        String socketType,

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
        @JsonProperty(value = "max_cpu_cooler_height")
        String maxCPUCoolerHeight,

        @JsonProperty(value = "max_gpu_length")
        String maxGPULength,

        @JsonProperty(value = "pre_installed_fans")
        String preInstalledFans,

        @JsonProperty(value = "tower_type")
        String towerType,


        //CPUCooler
        @JsonProperty(value = "fan_rpm")
        String fanRpm,

        //CPU
        @JsonProperty(value = "core_count")
        String coreCount,
        @JsonProperty(value = "core_clock")
        String coreClock,
        @JsonProperty(value = "boost_clock")
        String boostClock,
        @JsonProperty(value = "integrated_graphics")
        String integratedGraphics,


        //Internal Hard Drive
        @JsonProperty(value = "internal_hard_drive_type")
        String internalHardDriveInterfaceType,
        @JsonProperty(value = "capacity")
        String capacity,


        //GPU
        @JsonProperty(value = "gpu_interface_type")
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
        @JsonProperty(value = "count_of_ram")
        String countOfRam,
        @JsonProperty(value = "gb_of_ram")
        String gbOfRam,


//peripheral

        @JsonProperty(value = "cable_length")
        String cableLength,

        //Headset
        @JsonProperty(value = "frequency")
        String frequency,
        @JsonProperty(value = "connectivity")
        String connectivity,

        //Mouse
        @JsonProperty(value = "mouse_type")
        String mouseType,
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