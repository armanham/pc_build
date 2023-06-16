package com.bdg.pc_build.product.model.request.creation.main_component;

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
public class PowerSupplyCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'efficiency_rating' field can not be blank")
    @Pattern(
            regexp = EFFICIENCY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "efficiency_rating", required = true)
    String efficiencyRating;

    @NotBlank(message = "'wattage' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "wattage", required = true)
    String wattage;

    @NotBlank(message = "'modular_type' field can not be blank")
    @Pattern(
            regexp = MODULAR_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "modular_type", required = true)
    String modularType;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public PowerSupplyCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String efficiencyRating,
            final String wattage,
            final String modularType,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.modularType = modularType;
        this.tdp = tdp;
    }
}