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
public class KeyboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "connectivity_type can not be blank")
    @Pattern(
            regexp = CONNECTIVITY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "connectivity_type", required = true)
    private String connectivityType;

    @Positive(message = "cable_length can not be negative or zero")
    @JsonProperty(value = "cable_length", required = true)
    private Double cableLength;

    @NotBlank(message = "dimension can not be blank")
    @JsonProperty(value = "dimension", required = true)
    private String dimension;

    @Positive(message = "weight can not be negative or zero")
    @JsonProperty(value = "weight", required = true)
    private Double weight;

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