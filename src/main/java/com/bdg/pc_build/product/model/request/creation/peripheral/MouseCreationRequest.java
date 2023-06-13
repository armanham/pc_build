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
public class MouseCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'screen_type' field can not be blank")
    @JsonProperty(value = "screen_type", required = true)
    String mouseType;

    @NotBlank(message = "'max_resolution' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "max_resolution", required = true)
    String maxResolution;

    @NotBlank(message = "'cable_length' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "cable_length", required = true)
    String cableLength;

    @NotBlank(message = "'weight' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String weight;

    public MouseCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String mouseType,
            final String maxResolution,
            final String cableLength,
            final String weight
    ) {
        super(name, price, purchasedPrice, count);
        this.mouseType = mouseType.trim();
        this.maxResolution = maxResolution;
        this.cableLength = cableLength;
        this.weight = weight;
    }
}