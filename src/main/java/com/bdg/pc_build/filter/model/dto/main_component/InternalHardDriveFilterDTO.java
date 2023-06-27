package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.InternalHardDriveFilterRequest;
import com.bdg.pc_build.util.ValidationUtil;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class InternalHardDriveFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minCapacity;
    private final Integer maxCapacity;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<InternalHardDriveInterfaceType> internalHardDriveInterfaceTypes;

    public InternalHardDriveFilterDTO(final InternalHardDriveFilterRequest request) {
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

        if (request.internalHardDriveInterfaceTypes() != null && !request.internalHardDriveInterfaceTypes().isEmpty()) {
            this.internalHardDriveInterfaceTypes = request.internalHardDriveInterfaceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> InternalHardDriveInterfaceType.toListOfStrings().contains(s))
                    .map(InternalHardDriveInterfaceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.internalHardDriveInterfaceTypes = null;
        }
    }
}