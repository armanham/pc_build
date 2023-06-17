package com.bdg.pc_build.product.model.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class ProductCreationRequest {

    @NotBlank(message = "'name' field can not be blank")
    @JsonProperty(value = "name", required = true)
    String name;

    @NotBlank(message = "'price' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "price", required = true)
    String price;

    @NotBlank(message = "'purchased_price' field can not be blank")
    @Pattern(
            regexp = FLOATING_POINT_NUMBER_PATTERN,
            message = WRONG_FLOATING_POINT_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "purchased_price", required = true)
    String purchasedPrice;

    @NotBlank(message = "'count' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "count", required = true)
    String count;

    public ProductCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count
    ) {
        this.name = name.trim();
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }
}