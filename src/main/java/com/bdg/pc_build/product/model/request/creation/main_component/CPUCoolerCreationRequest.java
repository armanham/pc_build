package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import static com.bdg.pc_build.product.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CPUCoolerCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'fan_rpm' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "fan_rpm", required = true)
    String fanRpm;

    @NotBlank(message = "'socket_type' field can not be blank")
    @Pattern(
            regexp = SOCKET_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "socket_type", required = true)
    String socketType;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public CPUCoolerCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String fanRpm,
            final String socketType,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.fanRpm = fanRpm;
        this.socketType = socketType;
        this.tdp = tdp;
    }
}