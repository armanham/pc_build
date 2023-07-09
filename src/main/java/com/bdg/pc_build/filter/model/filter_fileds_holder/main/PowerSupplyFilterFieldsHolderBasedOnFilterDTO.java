package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.enumerations.ModularType;

import java.util.Set;

public record PowerSupplyFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minWattage, Integer maxWattage,
        Integer minTdp, Integer maxTdp,
        Set<ModularType> modularTypes,
        Set<EfficiencyRating> efficiencyRatings
) {
}