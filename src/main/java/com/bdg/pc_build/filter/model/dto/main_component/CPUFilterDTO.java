package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CPUFilterRequest;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CPUFilterDTO {

    Double minPrice;
    Double maxPrice;

    Integer minCoreCount;
    Integer maxCoreCount;

    Double minCoreClock;
    Double maxCoreClock;

    Double minBoostClock;
    Double maxBoostClock;

    Integer minTdp;
    Integer maxTdp;

    String integratedGraphics;

    SocketType socket;

    public CPUFilterDTO(final CPUFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}