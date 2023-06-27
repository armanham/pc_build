package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CPUFilterRequest;
import com.bdg.pc_build.util.ValidationUtil;
import com.bdg.pc_build.product.enumerations.SocketType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CPUFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minCoreCount;
    private final Integer maxCoreCount;

    private final Double minCoreClock;
    private final Double maxCoreClock;

    private final Double minBoostClock;
    private final Double maxBoostClock;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<String> integratedGraphics;

    private final Set<SocketType> socketTypes;

    public CPUFilterDTO(final CPUFilterRequest request) {
        this.name = request.name();
        this.minPrice = request.minPrice();

        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minCoreCount = request.minCoreCount();
        this.maxCoreCount = request.maxCoreCount();
        if (this.minCoreCount != null && this.maxCoreCount != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCoreCount, maxCoreCount);
        }

        this.minCoreClock = request.minCoreClock();
        this.maxCoreClock = request.maxCoreClock();
        if (this.minCoreClock != null && this.maxCoreClock != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCoreClock, maxCoreClock);
        }

        this.minBoostClock = request.minBoostClock();
        this.maxBoostClock = request.maxBoostClock();
        if (this.minBoostClock != null && this.maxBoostClock != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minBoostClock, maxBoostClock);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.integratedGraphics() != null && !request.integratedGraphics().isEmpty()) {
            this.integratedGraphics = request.integratedGraphics()
                    .stream()
                    .map(s -> s.toLowerCase().trim())
                    .collect(Collectors.toSet());
        } else {
            this.integratedGraphics = null;
        }

        if (request.socketTypes() != null && !request.socketTypes().isEmpty()) {
            this.socketTypes = request.socketTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> SocketType.toListOfStrings().contains(s))
                    .map(SocketType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.socketTypes = null;
        }
    }
}