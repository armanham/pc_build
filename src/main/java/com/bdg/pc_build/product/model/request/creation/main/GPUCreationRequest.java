package com.bdg.pc_build.product.model.request.creation.main;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.GPU_INTERFACE_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class GPUCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "gpu_interface_type field can not be blank")
    @Pattern(
            regexp = GPU_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "gpu_interface_type", required = true)
    private String gpuInterfaceType;

    @Positive(message = "memory can not be negative or zero")
    @JsonProperty(value = "memory", required = true)
    private Integer memory;

    @Positive(message = "core_clock can not be negative or zero")
    @JsonProperty(value = "core_clock", required = true)
    private Double coreClock;

    @Positive(message = "boost_clock can not be negative or zero")
    @JsonProperty(value = "boost_clock", required = true)
    private Double boostClock;

    @Positive(message = "length can not be negative or zero")
    @JsonProperty(value = "length", required = true)
    private Double length;

    @Positive(message = "tdp can not be negative or zero")
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public GPUCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String gpuInterfaceType,
            final Integer memory,
            final Double coreClock,
            final Double boostClock,
            final Double length,
            final Integer tdp) {
        super(name, price, purchasedPrice, count);
        this.gpuInterfaceType = gpuInterfaceType;
        this.memory = memory;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.length = length;
        this.tdp = tdp;
    }
}