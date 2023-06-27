package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.SOCKET_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class CPUCreationRequest extends ProductCreationRequest {

    @Positive
    @JsonProperty(value = "core_count", required = true)
    private Integer coreCount;

    @Positive
    @JsonProperty(value = "core_clock", required = true)
    private Double coreClock;

    @Positive
    @JsonProperty(value = "boost_clock", required = true)
    private Double boostClock;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    @NotBlank(message = "'integrated_graphics' field can not be blank")
    @JsonProperty(value = "integrated_graphics", required = true)
    private String integratedGraphics;

    @NotBlank(message = "'socket_type' field can not be blank")
    @Pattern(
            regexp = SOCKET_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "socket_type", required = true)
    private String socketType;

    public CPUCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer coreCount,
            final Double coreClock,
            final Double boostClock,
            final Integer tdp,
            final String integratedGraphics,
            final String socketType
    ) {
        super(name, price, purchasedPrice, count);
        this.coreCount = coreCount;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphics = integratedGraphics;
        this.socketType = socketType;
    }
}