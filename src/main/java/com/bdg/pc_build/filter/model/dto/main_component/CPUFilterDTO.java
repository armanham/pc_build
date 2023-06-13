package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.CPUFilterRequest;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CPUFilterDTO {

    final String name;

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

    final String integratedGraphics;

    SocketType socket;

    public CPUFilterDTO(final CPUFilterRequest request) {
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

        if (request.minCoreCount() != null && !request.minCoreCount().isBlank()) {
            this.minCoreCount = Integer.valueOf(request.minCoreCount());
        }
        if (request.maxCoreCount() != null && !request.maxCoreCount().isBlank()) {
            this.maxCoreCount = Integer.valueOf(request.maxCoreCount());
        }
        if (this.minCoreCount != null && this.maxCoreCount != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCoreCount, maxCoreCount);
        }

        if (request.minCoreClock() != null && !request.minCoreClock().isBlank()) {
            this.minCoreClock = Double.valueOf(request.minCoreClock());
        }
        if (request.maxCoreClock() != null && !request.maxCoreClock().isBlank()) {
            this.maxCoreClock = Double.valueOf(request.maxCoreClock());
        }
        if (this.minCoreClock != null && this.maxCoreClock != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCoreClock, maxCoreClock);
        }

        if (request.minBoostClock() != null && !request.minBoostClock().isBlank()) {
            this.minBoostClock = Double.valueOf(request.minBoostClock());
        }
        if (request.maxBoostClock() != null && !request.maxBoostClock().isBlank()) {
            this.maxBoostClock = Double.valueOf(request.maxBoostClock());
        }
        if (this.minBoostClock != null && this.maxBoostClock != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minBoostClock, maxBoostClock);
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