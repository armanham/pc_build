package com.bdg.pc_build.filter.model.dto;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import lombok.Getter;

@Getter
public class ProductFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    public ProductFilterDTO(final ProductFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }
    }
}