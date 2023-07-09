package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.GPUInterfaceType;

import java.util.Set;

public record GpuFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minMemory, Integer maxMemory,
        Double minCoreClock, Double maxCoreClock,
        Double minBoostClock, Double maxBoostClock,
        Double minLength, Double maxLength,
        Integer minTdp, Integer maxTdp,
        Set<GPUInterfaceType> gpuInterfaceTypes
) {
}