package com.bdg.pc_build.product.model.dto.main_component;

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

    String type;
    Integer capacity;
    Integer tdp;

    @Builder
    public ExternalHardDriveDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String type,
            final Integer capacity,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.type = type;
        this.capacity = capacity;
        this.tdp = tdp;
    }

    public static ExternalHardDriveDTO initDTOFromEntity(final ExternalHardDrive entity) {
        return ExternalHardDriveDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .type(String.valueOf(entity.getType()))
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static ExternalHardDriveDTO initDTOFromRequest(final ProductRequest request){
        return ExternalHardDriveDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .type(request.type())
                .capacity(request.capacity())
                .tdp(request.tdp())
                .build();
    }
}