package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.product.pattern.Pattern.DDR_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.product.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RAMCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'ddr_type' field can not be blank")
    @Pattern(
            regexp = DDR_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "ddr_type", required = true)
    String ddrType;

    @Positive
    @JsonProperty(value = "count_of_ram", required = true)
    Integer countOfRam;

    @Positive
    @JsonProperty(value = "gb_of_ram", required = true)
    Integer gbOfRam;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    Integer tdp;

    public RAMCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String ddrType,
            final Integer countOfRam,
            final Integer gbOfRam,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.ddrType = ddrType.toUpperCase();
        this.countOfRam = countOfRam;
        this.gbOfRam = gbOfRam;
        this.tdp = tdp;
    }
}