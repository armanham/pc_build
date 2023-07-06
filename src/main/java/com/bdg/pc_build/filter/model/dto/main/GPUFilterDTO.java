package com.bdg.pc_build.filter.model.dto.main;

import com.bdg.pc_build.filter.model.request.main.GPUFilterRequest;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class GPUFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minMemory;
    private final Integer maxMemory;

    private final Double minCoreClock;
    private final Double maxCoreClock;

    private final Double minBoostClock;
    private final Double maxBoostClock;

    private final Double minLength;
    private final Double maxLength;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<GPUInterfaceType> gpuInterfaceTypes;


    public GPUFilterDTO(final GPUFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minMemory = request.minMemory();
        this.maxMemory = request.maxMemory();
        if (this.minMemory != null && this.maxMemory != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minMemory, maxMemory);
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

        this.minLength = request.minLength();
        this.maxLength = request.maxLength();
        if (this.minLength != null && this.maxLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minLength, maxLength);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.gpuInterfaceTypes() != null && !request.gpuInterfaceTypes().isEmpty()) {
            this.gpuInterfaceTypes = request.gpuInterfaceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> GPUInterfaceType.toSetOfStrings().contains(s))
                    .map(GPUInterfaceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.gpuInterfaceTypes = null;
        }
    }
}