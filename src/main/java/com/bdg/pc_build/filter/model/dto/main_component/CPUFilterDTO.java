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

    String name;

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
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minCoreClock() != null && !request.minCoreClock().isBlank()) {
            this.minCoreClock = Double.valueOf(request.minCoreClock());
        }
        if (request.maxCoreClock() != null && !request.maxCoreClock().isBlank()) {
            this.maxCoreClock = Double.valueOf(request.maxCoreClock());
        }
        if (request.minBoostClock() != null && !request.minBoostClock().isBlank()) {
            this.minBoostClock = Double.valueOf(request.minBoostClock());
        }
        if (request.maxBoostClock() != null && !request.maxBoostClock().isBlank()) {
            this.maxBoostClock = Double.valueOf(request.maxBoostClock());
        }
        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        this.integratedGraphics = request.integratedGraphics();
        //TODO SOCKETTYPE
    }
}