package com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;

import java.util.Set;

public record KeyboardFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Double minCableLength, Double maxCableLength,
        Double minWeight, Double maxWeight,
        Set<String> dimensions,
        Set<ConnectivityType> connectivityTypes
) {
}