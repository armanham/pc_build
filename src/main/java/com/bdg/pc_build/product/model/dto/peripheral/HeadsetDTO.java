package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.enumerations.Connectivity;
import com.bdg.pc_build.product.model.request.creation.peripheral.HeadsetCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class HeadsetDTO extends ProductDTO {

    Integer frequency;
    Connectivity connectivityHeadset;
    Double cableLength;

    @Builder
    public HeadsetDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final Connectivity connectivity,
            final Double cableLength
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.connectivityHeadset = connectivity;
        this.cableLength = cableLength;
    }

    public static HeadsetDTO initDTOFromEntity(final Headset entity) {
        return HeadsetDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .frequency(entity.getFrequency())
                .connectivity(entity.getConnectivityHeadset())
                .cableLength(entity.getCableLength())
                .build();
    }

    public static HeadsetDTO initDTOFromRequest(final HeadsetCreationRequest request) {
        return HeadsetDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .frequency(Integer.valueOf(request.getFrequency()))
                .connectivity(Connectivity.valueOf(request.getConnectivityHeadset()))
                .cableLength(Double.valueOf(request.getCableLength()))
                .build();
    }
}