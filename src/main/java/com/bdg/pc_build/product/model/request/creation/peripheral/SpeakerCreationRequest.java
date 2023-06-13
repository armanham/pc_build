package com.bdg.pc_build.product.model.request.creation.peripheral;

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
public class SpeakerCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'frequency' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "frequency", required = true)
    String frequency;

    @NotBlank(message = "'power_source' field can not be blank")
    @JsonProperty(value = "power_source", required = true)
    String powerSource;

    @NotBlank(message = "'cable_length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "cable_length", required = true)
    String cableLength;

    @NotBlank(message = "'dimension' field can not be blank")
    @JsonProperty(value = "dimension", required = true)
    String dimension;

    public SpeakerCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String frequency,
            final String powerSource,
            final String cableLength,
            final String dimension
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.powerSource = powerSource.trim();
        this.cableLength = cableLength;
        this.dimension = dimension.trim();
    }
}