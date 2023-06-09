package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class MouseDTO extends ProductDTO {

    String type;
    Integer maxResolution;
    Double cableLength;
    Double weight;

    @Builder
    public MouseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String type,
            final Integer maxResolution,
            final Double cableLength,
            final Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.type = type;
        this.maxResolution = maxResolution;
        this.cableLength = cableLength;
        this.weight = weight;
    }

    public static MouseDTO initDTOFromEntity(final Mouse entity) {
        return MouseDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .type(entity.getType())
                .maxResolution(entity.getMaxResolution())
                .cableLength(entity.getCableLength())
                .weight(entity.getWeight())
                .build();
    }

    public static MouseDTO initDTOFromRequest(final ProductRequest request){
        return MouseDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .type(request.type())
                .maxResolution(request.maxResolution())
                .cableLength(request.cableLength())
                .weight(request.weight())
                .build();
    }

}
