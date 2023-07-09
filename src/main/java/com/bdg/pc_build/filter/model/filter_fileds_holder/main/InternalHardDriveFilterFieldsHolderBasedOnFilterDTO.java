package com.bdg.pc_build.filter.model.filter_fileds_holder.main;

import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;

import java.util.Set;

public record InternalHardDriveFilterFieldsHolderBasedOnFilterDTO(
        String name,
        Double minPrice, Double maxPrice,
        Integer minCapacity, Integer maxCapacity,
        Integer minTdp, Integer maxTdp,
        Set<InternalHardDriveInterfaceType> internalHardDriveInterfaceTypes
) {
}