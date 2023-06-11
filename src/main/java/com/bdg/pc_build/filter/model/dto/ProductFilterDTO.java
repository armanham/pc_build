package com.bdg.pc_build.filter.model.dto;

import com.bdg.pc_build.filter.model.request.ProductFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ProductFilterDTO {

    String name;

    Double minPrice;

    Double maxPrice;

    public ProductFilterDTO(final ProductFilterRequest request) {
        this.name = request.name();
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}