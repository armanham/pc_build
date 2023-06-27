package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.InternalHardDriveFilterRequest;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class InternalHardDriveFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    private Integer minCapacity;
    private Integer maxCapacity;

    private Integer minTdp;
    private Integer maxTdp;

    private final Set<InternalHardDriveInterfaceType> internalHardDriveInterfaceTypes;

    public InternalHardDriveFilterDTO(final InternalHardDriveFilterRequest request) {
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