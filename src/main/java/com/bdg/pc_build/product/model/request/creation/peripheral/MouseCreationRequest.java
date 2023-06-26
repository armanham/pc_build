package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.product.pattern.Pattern.CONNECTIVITY_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MouseCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'connectivity_type' field can not be blank")
    @Pattern(
            regexp = CONNECTIVITY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "connectivity_type", required = true)
    String connectivityType;

    @Positive
    @JsonProperty(value = "max_resolution", required = true)
    Integer maxResolution;

    @Positive
    @JsonProperty(value = "cable_length", required = true)
    Double cableLength;

    @Positive
    @JsonProperty(value = "weight", required = true)
    Double weight;

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