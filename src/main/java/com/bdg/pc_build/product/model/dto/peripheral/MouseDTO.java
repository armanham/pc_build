package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.request.creation.peripheral.MouseCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class MouseDTO extends ProductDTO {

    String mouseType;
    Integer maxResolution;
    Double cableLength;
    Double weight;

    @Builder
    public MouseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String mouseType,
            final Integer maxResolution,
            final Double cableLength,
            final Double weight
    ) {
        super(name, price, purchasedPrice, count);
        this.mouseType = mouseType;
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
                .mouseType(entity.getMouseType())
                .maxResolution(entity.getMaxResolution())
                .cableLength(entity.getCableLength())
                .weight(entity.getWeight())
                .build();
    }

    public static MouseDTO initDTOFromRequest(final MouseCreationRequest request) {
        return MouseDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .mouseType(request.getMouseType())
                .maxResolution(Integer.valueOf(request.getMaxResolution()))
                .cableLength(Double.valueOf(request.getCableLength()))
                .weight(Double.valueOf(request.getWeight()))
                .build();
    }
}