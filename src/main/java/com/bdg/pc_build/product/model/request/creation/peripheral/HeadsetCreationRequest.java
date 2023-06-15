package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class HeadsetCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'frequency' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "frequency", required = true)
    String frequency;

    @NotBlank(message = "'connectivity_type' field can not be blank")
    @Pattern(
            regexp = CONNECTIVITY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "connectivity_type", required = true)
    String connectivityType;

    @NotBlank(message = "'cable_length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "cable_length", required = true)
    String cableLength;

    public HeadsetCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String frequency,
            final String connectivityType,
            final String cableLength
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.connectivityType = connectivityType;
        this.cableLength = cableLength;
    }
}