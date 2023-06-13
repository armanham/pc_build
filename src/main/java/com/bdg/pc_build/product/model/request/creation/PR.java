package com.bdg.pc_build.product.model.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.bdg.pc_build.checking.pattern.Pattern.*;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE;
@NoArgsConstructor
@Getter
@Setter
public class PR {

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

    public PR(String name, String price, String purchasedPrice, String count) {
        this.name = name;
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }
}