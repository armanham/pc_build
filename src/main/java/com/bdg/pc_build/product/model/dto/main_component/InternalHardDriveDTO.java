package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class InternalHardDriveDTO extends ProductDTO {

    InternalHardDriveInterfaceType internalHardDriveInterfaceType;
    Integer capacity;
    Integer tdp;

    @Builder
    public InternalHardDriveDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final InternalHardDriveInterfaceType internalHardDriveInterfaceType,
            final Integer capacity,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.internalHardDriveInterfaceType = internalHardDriveInterfaceType;
        this.capacity = capacity;
        this.tdp = tdp;
    }

    public static InternalHardDriveDTO initDTOFromEntity(final InternalHardDrive entity) {
        return InternalHardDriveDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .internalHardDriveInterfaceType(entity.getInternalHardDriveInterfaceType())
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static InternalHardDriveDTO initDTOFromRequest(final ProductRequest request) {
        return InternalHardDriveDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .internalHardDriveInterfaceType(InternalHardDriveInterfaceType.valueOf(request.internalHardDriveInterfaceType()))
                .capacity(Integer.valueOf(request.capacity()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}