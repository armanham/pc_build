package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.request.creation.main_component.InternalHardDriveCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class InternalHardDriveDTO extends ProductDTO {

    private final InternalHardDriveInterfaceType internalHardDriveInterfaceType;
    private final Integer capacity;
    private final Integer tdp;

    @Builder
    public InternalHardDriveDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final InternalHardDriveInterfaceType internalHardDriveInterfaceType,
            final Integer capacity,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.internalHardDriveInterfaceType = internalHardDriveInterfaceType;
        this.capacity = capacity;
        this.tdp = tdp;
    }

    public static InternalHardDriveDTO initDTOFromEntity(final InternalHardDrive entity) {
        return InternalHardDriveDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .internalHardDriveInterfaceType(entity.getInternalHardDriveInterfaceType())
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static InternalHardDriveDTO initDTOFromRequest(final InternalHardDriveCreationRequest request) {
        if ((request.getCapacity() & request.getCapacity() - 1) != 0) {
            throw new IllegalArgumentException("'capacity' must be power of two");
        }
        return InternalHardDriveDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .internalHardDriveInterfaceType(InternalHardDriveInterfaceType.valueOf(request.getInternalHardDriveInterfaceType().trim().toUpperCase()))
                .capacity(request.getCapacity())
                .tdp(request.getTdp())
                .build();
    }
}