package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.FLOATING_POINT_NUMBER_PATTERN;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class KeyboardCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'connectivityKeyboard' field can not be blank")
    @JsonProperty(value = "c", required = true)
    String connectivityKeyboard;

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

    @NotBlank(message = "'weight' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "weight", required = true)
    String weight;

    public KeyboardCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String connectivityKeyboard,
            final String cableLength,
            final String dimension,
            final String weight
    ) {
        super(name, price, purchasedPrice, count);
        this.connectivityKeyboard = connectivityKeyboard.trim();
        this.cableLength = cableLength;
        this.dimension = dimension.trim();
        this.weight = weight;
    }
}