package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.GPUFilterRequest;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class GPUFilterDTO {

    Double minPrice;
    Double maxPrice;

    GPUInterfaceType gpuInterfaceType;
    Integer minMemory;
    Integer maxMemory;

    Double minCoreClock;
    Double maxCoreClock;

    Double minBoostClock;
    Double maxBoostClock;

    Double minLength;
    Double maxLength;

    Integer minTdp;
    Integer maxTdp;

    public GPUFilterDTO(final GPUFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}