package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.model.request.creation.peripheral.ExternalHardDriveCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExternalHardDriveDTO extends ProductDTO {

    private final Integer capacity;
    private final Integer tdp;

    @Builder
    public ExternalHardDriveDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer capacity,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.capacity = capacity;
        this.tdp = tdp;
    }

    public static ExternalHardDriveDTO initDTOFromEntity(final ExternalHardDrive entity) {
        return ExternalHardDriveDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static ExternalHardDriveDTO initDTOFromRequest(final ExternalHardDriveCreationRequest request) {
        if ((request.getCapacity() & request.getCapacity() - 1) != 0) {
            throw new IllegalArgumentException("'capacity' must be power of two");
        }
        return ExternalHardDriveDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .capacity(request.getCapacity())
                .tdp(request.getTdp())
                .build();
    }
}