package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.*;

@Getter
public class PowerSupplyCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'efficiency_rating' field can not be blank")
    @Pattern(
            regexp = EFFICIENCY_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "efficiency_rating", required = true)
    private String efficiencyRating;

    @Positive
    @JsonProperty(value = "wattage", required = true)
    private Integer wattage;

    @NotBlank(message = "'modular_type' field can not be blank")
    @Pattern(
            regexp = MODULAR_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "modular_type", required = true)
    private String modularType;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public PowerSupplyCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String efficiencyRating,
            final Integer wattage,
            final String modularType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.modularType = modularType;
        this.tdp = tdp;
    }
}