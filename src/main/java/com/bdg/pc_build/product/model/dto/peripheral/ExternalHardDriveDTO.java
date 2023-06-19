package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.model.request.creation.peripheral.ExternalHardDriveCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class ExternalHardDriveDTO extends ProductDTO {

    Integer capacity;
    Integer tdp;

    @Builder
    public ExternalHardDriveDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer capacity,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.capacity = capacity;
        this.tdp = tdp;
    }

    public static ExternalHardDriveDTO initDTOFromEntity(final ExternalHardDrive entity) {
        return ExternalHardDriveDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static ExternalHardDriveDTO initDTOFromRequest(final ExternalHardDriveCreationRequest request) {
        if ((Integer.parseInt(request.getCapacity()) & Integer.parseInt(request.getCapacity()) - 1) != 0) {
            throw new IllegalArgumentException("'capacity' must be power of two");
        }
        return ExternalHardDriveDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .capacity(Integer.valueOf(request.getCapacity()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}