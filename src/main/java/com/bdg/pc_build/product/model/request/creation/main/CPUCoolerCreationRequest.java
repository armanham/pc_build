package com.bdg.pc_build.product.model.request.creation.main;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.SOCKET_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class CPUCoolerCreationRequest extends ProductCreationRequest {

    @Positive(message = "fan_rpm can not be negative or zero")
    @JsonProperty(value = "fan_rpm", required = true)
    private Integer fanRpm;

    @NotBlank(message = "socket_type can not be blank")
    @Pattern(
            regexp = SOCKET_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "socket_type", required = true)
    private String socketType;

    @Positive(message = "tdp can not be negative or zero")
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public CPUCoolerCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer fanRpm,
            final String socketType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.fanRpm = fanRpm;
        this.socketType = socketType;
        this.tdp = tdp;
    }
}