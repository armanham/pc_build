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
public class KeyboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'connectivity_type' field can not be blank")
    @Pattern(
            regexp = CONNECTIVITY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "connectivity_type", required = true)
    String connectivityType;

    @Positive
    @JsonProperty(value = "cable_length", required = true)
    Double cableLength;

    @NotBlank(message = "'dimension' field can not be blank")
    @JsonProperty(value = "dimension", required = true)
    String dimension;

    @Positive
    @JsonProperty(value = "weight", required = true)
    Double weight;

    public KeyboardCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String connectivityType,
            final Double cableLength,
            final String dimension,
            final Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.connectivityType = connectivityType;
        this.cableLength = cableLength;
        this.dimension = dimension;
        this.weight = weight;
    }
}