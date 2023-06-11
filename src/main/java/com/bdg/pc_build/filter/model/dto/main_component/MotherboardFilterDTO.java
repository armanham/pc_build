package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.MotherboardFilterRequest;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class MotherboardFilterDTO {

    Double minPrice;
    Double maxPrice;

    SocketType socketType;

    ATXType atxType;

    Integer minMemory;
    Integer maxMemory;

    Integer minMemorySlots;
    Integer maxMemorySlots;

    DDRType memoryType;

    String internalConnections;

    GPUInterfaceType gpuInterfaceType;

    Integer minTdp;
    Integer maxTdp;

    public MotherboardFilterDTO(final MotherboardFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}