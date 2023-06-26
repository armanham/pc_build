package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.product.pattern.Pattern.GPU_INTERFACE_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class GPUCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'gpu_interface_type' field can not be blank")
    @Pattern(
            regexp = GPU_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "gpu_interface_type", required = true)
    String gpuInterfaceType;

    @Positive
    @JsonProperty(value = "memory", required = true)
    Integer memory;

    @Positive
    @JsonProperty(value = "core_clock", required = true)
    Double coreClock;

    @Positive
    @JsonProperty(value = "boost_clock", required = true)
    Double boostClock;

    @Positive
    @JsonProperty(value = "length", required = true)
    Double length;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    Integer tdp;

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