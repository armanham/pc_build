package com.bdg.pc_build.product.model.request.creation.peripheral;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ExternalHardDriveCreationRequest extends ProductCreationRequest {

    @Positive
    @JsonProperty(value = "capacity", required = true)
    private Integer capacity;

    @Positive
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public ExternalHardDriveCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer capacity,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.capacity = capacity;
        this.tdp = tdp;
    }
}