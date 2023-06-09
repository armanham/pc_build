package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.InternalHardDrive;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class InternalHardDriveDTO extends ProductDTO {

    Integer capacity;
    Integer tdp;

    @Builder
    public InternalHardDriveDTO(
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

    public static InternalHardDriveDTO initDTOFromEntity(final InternalHardDrive entity) {
        return InternalHardDriveDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .capacity(entity.getCapacity())
                .tdp(entity.getTdp())
                .build();
    }

    public static InternalHardDriveDTO initDTOFromRequest(final ProductRequest request){
        return InternalHardDriveDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .capacity(request.capacity())
                .tdp(request.tdp())
                .build();
    }
}