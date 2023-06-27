package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.CONNECTIVITY_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class MouseCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'connectivity_type' field can not be blank")
    @Pattern(
            regexp = CONNECTIVITY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "connectivity_type", required = true)
    private String connectivityType;

    @Positive
    @JsonProperty(value = "max_resolution", required = true)
    private Integer maxResolution;

    @Positive
    @JsonProperty(value = "cable_length", required = true)
    private Double cableLength;

    @Positive
    @JsonProperty(value = "weight", required = true)
    private Double weight;

    public MouseCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String connectivityType,
            final Integer maxResolution,
            final Double cableLength,
            final Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.connectivityType = connectivityType;
        this.maxResolution = maxResolution;
        this.cableLength = cableLength;
        this.weight = weight;
    }
}