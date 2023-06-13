package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.*;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class GPUCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'gpu_interface_type' field can not be blank")
    @JsonProperty(value = "gpu_interface_type", required = true)
    String gpuInterfaceType;

    @NotBlank(message = "'memory' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "memory", required = true)
    String memory;

    @NotBlank(message = "'core_clock' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "core_clock", required = true)
    String coreClock;

    @NotBlank(message = "'boost_clock' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "boost_clock", required = true)
    String boostClock;

    @NotBlank(message = "'length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "length", required = true)
    String length;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public GPUCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String gpuInterfaceType,
            final String memory,
            final String coreClock,
            final String boostClock,
            final String length,
            final String tdp) {
        super(name, price, purchasedPrice, count);
        this.gpuInterfaceType = gpuInterfaceType.trim();
        this.memory = memory;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.length = length;
        this.tdp = tdp;
    }
}