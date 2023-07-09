package com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral;

import com.bdg.pc_build.product.enumerations.MonitorScreenType;

import java.util.Set;

public record MonitorFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Double minScreenSize, Double maxScreenSize,
        Integer minRefreshRate, Integer maxRefreshRate,
        Set<MonitorScreenType> monitorScreenTypes
) {
}