package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.product.pattern.Pattern.POWER_SOURCE_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class SpeakerCreationRequest extends ProductCreationRequest {

    @Positive
    @JsonProperty(value = "frequency", required = true)
    private Integer frequency;

    @NotBlank(message = "'power_source_type' field can not be blank")
    @Pattern(
            regexp = POWER_SOURCE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "power_source_type", required = true)
    private String powerSourceType;

    @Positive
    @JsonProperty(value = "cable_length", required = true)
    private Double cableLength;

    @NotBlank(message = "'dimension' field can not be blank")
    @JsonProperty(value = "dimension", required = true)
    private String dimension;

    public SpeakerCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final String powerSource,
            final Double cableLength,
            final String dimension
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.powerSourceType = powerSource;
        this.cableLength = cableLength;
        this.dimension = dimension.trim();
    }
}