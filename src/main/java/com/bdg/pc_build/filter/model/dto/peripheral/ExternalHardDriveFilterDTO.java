package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.util.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.ExternalHardDriveFilterRequest;
import lombok.Getter;

@Getter
public class ExternalHardDriveFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minCapacity;
    private final Integer maxCapacity;

    private final Integer minTdp;
    private final Integer maxTdp;

    public ExternalHardDriveFilterDTO(final ExternalHardDriveFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minCapacity = request.minCapacity();
        this.maxCapacity = request.maxCapacity();
        if (this.minCapacity != null && this.maxCapacity != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCapacity, maxCapacity);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }
    }
}