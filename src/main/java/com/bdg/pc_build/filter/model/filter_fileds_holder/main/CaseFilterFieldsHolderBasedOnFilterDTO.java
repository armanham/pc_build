package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.TowerType;

import java.util.Set;

public record CaseFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Double minCpuCoolerHeight, Double maxCpuCoolerHeight,
        Double minGpuLength, Double maxGpuLength,
        Integer minPreInstalledFans, Integer maxPreInstalledFans,
        Set<TowerType> towerTypes
) {
}