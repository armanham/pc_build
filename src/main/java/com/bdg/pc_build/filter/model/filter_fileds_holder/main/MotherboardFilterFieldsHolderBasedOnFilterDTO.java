package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;

import java.util.Set;

public record MotherboardFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minMemory, Integer maxMemory,
        Integer minMemorySlots, Integer maxMemorySlots,
        Integer minTdp, Integer maxTdp,
        Set<Boolean> isM2,
        Set<DDRType> ddrTypes,
        Set<GPUInterfaceType> gpuInterfaceTypes,
        Set<SocketType> socketTypes,
        Set<ATXType> atxTypes
) {
}