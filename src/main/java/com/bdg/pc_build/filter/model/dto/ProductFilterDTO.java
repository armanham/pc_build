package com.bdg.pc_build.filter.model.dto;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import lombok.Getter;

@Getter
public class ProductFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    public ProductFilterDTO(final ProductFilterRequest request) {
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }
    }
}