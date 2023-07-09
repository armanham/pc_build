package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.DDRType;

import java.util.Set;

public record RamFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minCountOfRam, Integer maxCountOfRam,
        Integer minGbOfRam, Integer maxGbOfRam,
        Integer minTdp, Integer maxTdp,
        Set<DDRType> ddrTypes
) {
}