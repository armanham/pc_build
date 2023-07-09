package com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;

import java.util.Set;

public record SpeakerFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minFrequency, Integer maxFrequency,
        Double minCableLength, Double maxCableLength,
        Set<String> dimensions,
        Set<PowerSourceType> powerSourceTypes
) {
}