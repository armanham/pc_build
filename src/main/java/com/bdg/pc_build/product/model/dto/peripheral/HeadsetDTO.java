package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class HeadsetDTO extends ProductDTO {

    Integer frequency;
    String connectivity;
    Double cableLength;

    @Builder
    public HeadsetDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final String connectivity,
            final Double cableLength
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.connectivity = connectivity;
        this.cableLength = cableLength;
    }

    public static HeadsetDTO initDTOFromEntity(final Headset entity) {
        return HeadsetDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .frequency(entity.getFrequency())
                .connectivity(entity.getConnectivity())
                .cableLength(entity.getCableLength())
                .build();
    }

    public static HeadsetDTO initDTOFromRequest(final ProductRequest request){
        return HeadsetDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .frequency(Integer.valueOf(request.frequency()))
                .connectivity(request.connectivity())
                .cableLength(Double.valueOf(request.cableLength()))
                .build();
    }
}
