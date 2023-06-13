package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.MotherboardFilterRequest;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MotherboardFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minMemory;
    Integer maxMemory;

    Integer minMemorySlots;
    Integer maxMemorySlots;

    Integer minTdp;
    Integer maxTdp;

    final String internalConnections;

    DDRType memoryType;

    GPUInterfaceType gpuInterfaceType;

    SocketType socketType;

    ATXType atxType;

    public MotherboardFilterDTO(final MotherboardFilterRequest request) {
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
            ValidationUtil.validateNonNegativeMinMaxValues(maxMemory, maxMemory);
        }

        if (request.minMemorySlots() != null && !request.minMemorySlots().isBlank()) {
            this.minMemorySlots = Integer.valueOf(request.minMemorySlots());
        }
        if (request.maxMemorySlots() != null && !request.maxMemorySlots().isBlank()) {
            this.maxMemorySlots = Integer.valueOf(request.maxMemorySlots());
        }
        if (this.minMemorySlots != null && this.maxMemorySlots != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minMemorySlots, maxMemorySlots);
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

        this.internalConnections = request.internalConnections();
        //TODO DDRType memoryType;
        //TODO GPUInterfaceType gpuInterfaceType;
        //TODO SocketType socketType;
        //TODO ATXType atxType;
    }
}