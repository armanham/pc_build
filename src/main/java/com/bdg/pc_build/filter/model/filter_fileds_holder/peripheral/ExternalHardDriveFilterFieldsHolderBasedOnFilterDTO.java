package com.bdg.pc_build.filter.model.filter_fileds_holder.peripheral;

public record ExternalHardDriveFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minCapacity, Integer maxCapacity,
        Integer minTdp, Integer maxTdp
) {
}