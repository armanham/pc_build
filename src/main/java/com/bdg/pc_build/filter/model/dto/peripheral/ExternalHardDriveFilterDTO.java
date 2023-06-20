package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.ExternalHardDriveFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class ExternalHardDriveFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minCapacity;
    Integer maxCapacity;

    Integer minTdp;
    Integer maxTdp;

    public ExternalHardDriveFilterDTO(final ExternalHardDriveFilterRequest request) {
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        if (request.minCapacity() != null && !request.minCapacity().isBlank()) {
            this.minCapacity = Integer.valueOf(request.minCapacity());
        }
        if (request.maxCapacity() != null && !request.maxCapacity().isBlank()) {
            this.maxCapacity = Integer.valueOf(request.maxCapacity());
        }
        if (this.minCapacity != null && this.maxCapacity != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCapacity, maxCapacity);
        }

        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }
    }
}