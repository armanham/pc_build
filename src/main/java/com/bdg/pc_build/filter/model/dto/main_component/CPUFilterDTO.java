package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.CPUFilterRequest;
import com.bdg.pc_build.product.enumerations.SocketType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CPUFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    private Integer minCoreCount;
    private Integer maxCoreCount;

    private Double minCoreClock;
    private Double maxCoreClock;

    private Double minBoostClock;
    private Double maxBoostClock;

    private Integer minTdp;
    private Integer maxTdp;

    private final Set<String> integratedGraphics;

    private final Set<SocketType> socketTypes;

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