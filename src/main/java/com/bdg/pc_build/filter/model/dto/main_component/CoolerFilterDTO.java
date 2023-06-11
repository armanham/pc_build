package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CoolerFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CoolerFilterDTO {

    Double minPrice;
    Double maxPrice;

    Integer minTdp;
    Integer maxTdp;

    public CoolerFilterDTO(final CoolerFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}