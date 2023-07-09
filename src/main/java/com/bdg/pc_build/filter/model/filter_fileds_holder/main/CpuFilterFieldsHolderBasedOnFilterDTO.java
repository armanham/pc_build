package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.SocketType;

import java.util.Set;

public record CpuFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minCoreCount, Integer maxCoreCount,
        Double minCoreClock, Double maxCoreClock,
        Double minBoostClock, Double maxBoostClock,
        Integer minTdp, Integer maxTdp,
        Set<String> integratedGraphics,
        Set<SocketType> socketTypes
) {
}