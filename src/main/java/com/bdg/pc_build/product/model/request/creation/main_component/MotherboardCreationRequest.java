package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.product.pattern.Pattern.*;

@Getter
public class MotherboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'socket_type' field can not be blank")
    @Pattern(
            regexp = SOCKET_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "socket_type", required = true)
    private String socketType;

    @NotBlank(message = "'atx_type' field can not be blank")
    @Pattern(
            regexp = ATX_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "atx_type", required = true)
    private String atxType;

    @Positive
    @JsonProperty(value = "memory_max", required = true)
    private Integer memoryMax;

    @Positive
    @JsonProperty(value = "memory_slots", required = true)
    private Integer memorySlots;

    @NotBlank(message = "'ddr_type' field can not be blank")
    @Pattern(
            regexp = DDR_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "ddr_type", required = true)
    private String ddrType;

    @NotBlank(message = "'is_m2' field can not be blank")
    @Pattern(
            regexp = BOOLEAN_PATTERN,
            message = WRONG_BOOLEAN_PATTERN
    )
    @JsonProperty(value = "is_m2", required = true)
    private String isM2;

    @NotBlank(message = "'gpu_interface_type' field can not be blank")
    @Pattern(
            regexp = GPU_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "gpu_interface_type", required = true)
    private String gpuInterfaceType;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public MotherboardCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String socketType,
            final String atxType,
            final Integer memoryMax,
            final Integer memorySlots,
            final String ddrType,
            final String isM2,
            final String gpuInterfaceType,
            final Integer tdp
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