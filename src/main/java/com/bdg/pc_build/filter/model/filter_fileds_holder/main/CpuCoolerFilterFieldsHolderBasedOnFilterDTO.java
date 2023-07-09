package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.SocketType;

import java.util.Set;

public record CpuCoolerFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minFanRpm, Integer maxFanRpm,
        Integer minTdp, Integer maxTdp,
        Set<SocketType> socketTypes
) {
}