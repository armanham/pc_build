package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
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

    public static ExternalHardDriveDTO initDTOFromRequest(final ProductRequest request) {
        return ExternalHardDriveDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .capacity(Integer.valueOf(request.capacity()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}