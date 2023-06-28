package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CoolerCreationRequest extends ProductCreationRequest {

    @Positive(message = "tdp can not be negative or zero")
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public CoolerCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.tdp = tdp;
    }
}