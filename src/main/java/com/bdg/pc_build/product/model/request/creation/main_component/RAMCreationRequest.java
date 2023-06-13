package com.bdg.pc_build.product.model.request.creation.main_component;

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
public class RAMCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'speed' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "speed", required = true)
    String speed;

    @NotBlank(message = "'count_of_ram' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "count_of_ram", required = true)
    String countOfRam;

    @NotBlank(message = "'gb_of_ram' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "gb_of_ram", required = true)
    String gbOfRam;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public RAMCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String speed,
            final String countOfRam,
            final String gbOfRam,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.speed = speed;
        this.countOfRam = countOfRam;
        this.gbOfRam = gbOfRam;
        this.tdp = tdp;
    }
}