package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.InternalHardDriveFilterRequest;
import com.bdg.pc_build.product.model.enumerations.InternalHardDriveInterfaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class InternalHardDriveFilterDTO {

    Double minPrice;
    Double maxPrice;

    InternalHardDriveInterfaceType type;

    Integer minCapacity;
    Integer maxCapacity;

    Integer minTdp;
    Integer maxTdp;

    public InternalHardDriveFilterDTO(final InternalHardDriveFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}