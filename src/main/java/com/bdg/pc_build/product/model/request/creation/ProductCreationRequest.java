package com.bdg.pc_build.product.model.request.creation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductCreationRequest {

    @NotBlank(message = "name field can not be blank")
    @JsonProperty(value = "name", required = true)
    private String name;

    @Positive(message = "price can not be negative or zero")
    @JsonProperty(value = "price", required = true)
    private Double price;

    @Positive(message = "purchased_price can not be negative or zero")
    @JsonProperty(value = "purchased_price", required = true)
    private Double purchasedPrice;

    @Min(value = 0, message = "count can not be negative")
    @JsonProperty(value = "count", required = true)
    private Integer count;

    public ProductCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count
    ) {
        this.name = name;
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }
}