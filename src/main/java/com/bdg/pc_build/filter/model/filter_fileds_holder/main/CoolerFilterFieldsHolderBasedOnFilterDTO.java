package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

public record CoolerFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minTdp, Integer maxTdp
) {
}