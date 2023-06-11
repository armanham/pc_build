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

    GPUInterfaceType gpuInterfaceType;


    public GPUFilterDTO(final GPUFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minMemory() != null && !request.minMemory().isBlank()) {
            this.minMemory = Integer.valueOf(request.minMemory());
        }
        if (request.maxMemory() != null && !request.maxMemory().isBlank()) {
            this.maxMemory = Integer.valueOf(request.maxMemory());
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
        if (request.minLength() != null && !request.minLength().isBlank()) {
            this.minLength = Double.valueOf(request.minLength());
        }
        if (request.maxLength() != null && !request.maxLength().isBlank()) {
            this.maxLength = Double.valueOf(request.maxLength());
        }
        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        //TODO GPUINTERFACETYPE
    }
}