package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MotherboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'socket_type' field can not be blank")
    @Pattern(
            regexp = SOCKET_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "socket_type", required = true)
    String socketType;

    @NotBlank(message = "'atx_type' field can not be blank")
    @Pattern(
            regexp = ATX_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "atx_type", required = true)
    String atxType;

    @NotBlank(message = "'memory_max' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "memory_max", required = true)
    String memoryMax;

    @NotBlank(message = "'memory_slots' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "memory_slots", required = true)
    String memorySlots;

    @NotBlank(message = "'ddr_type' field can not be blank")
    @Pattern(
            regexp = DDR_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "ddr_type", required = true)
    String ddrType;

    @NotBlank(message = "'is_m2' field can not be blank")
    @Pattern(
            regexp = BOOLEAN_PATTERN,
            message = WRONG_BOOLEAN_PATTERN
    )
    @JsonProperty(value = "is_m2", required = true)
    String isM2;

    @NotBlank(message = "'gpu_interface_type' field can not be blank")
    @Pattern(
            regexp = GPU_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "gpu_interface_type", required = true)
    String gpuInterfaceType;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public MotherboardCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String socketType,
            final String atxType,
            final String memoryMax,
            final String memorySlots,
            final String ddrType,
            final String isM2,
            final String gpuInterfaceType,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.socketType = socketType;
        this.atxType = atxType;
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.ddrType = ddrType;
        this.isM2 = isM2;
        this.gpuInterfaceType = gpuInterfaceType;
        this.tdp = tdp;
    }
}