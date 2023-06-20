package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.GPUFilterRequest;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class GPUFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

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

    final Set<GPUInterfaceType> gpuInterfaceTypes;


    public GPUFilterDTO(final GPUFilterRequest request) {
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

        if (request.minMemory() != null && !request.minMemory().isBlank()) {
            this.minMemory = Integer.valueOf(request.minMemory());
        }
        if (request.maxMemory() != null && !request.maxMemory().isBlank()) {
            this.maxMemory = Integer.valueOf(request.maxMemory());
        }
        if (this.minMemory != null && this.maxMemory != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minMemory, maxMemory);
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

        if (request.minLength() != null && !request.minLength().isBlank()) {
            this.minLength = Double.valueOf(request.minLength());
        }
        if (request.maxLength() != null && !request.maxLength().isBlank()) {
            this.maxLength = Double.valueOf(request.maxLength());
        }
        if (this.minLength != null && this.maxLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minLength, maxLength);
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

        if (request.gpuInterfaceTypes() != null && !request.gpuInterfaceTypes().isEmpty()) {
            this.gpuInterfaceTypes = request.gpuInterfaceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> GPUInterfaceType.toListOfStrings().contains(s))
                    .map(GPUInterfaceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.gpuInterfaceTypes = null;
        }
    }
}