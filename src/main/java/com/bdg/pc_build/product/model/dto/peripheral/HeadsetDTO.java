package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.request.creation.peripheral.HeadsetCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HeadsetDTO extends ProductDTO {

    private final Integer frequency;
    private final ConnectivityType connectivityType;
    private final Double cableLength;

    @Builder
    public HeadsetDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final ConnectivityType connectivityType,
            final Double cableLength
    ) {
        super(id, name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.connectivityType = connectivityType;
        this.cableLength = cableLength;
    }

    public static HeadsetDTO initDTOFromEntity(final Headset entity) {
        return HeadsetDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .frequency(entity.getFrequency())
                .connectivityType(entity.getConnectivityType())
                .cableLength(entity.getCableLength())
                .build();
    }

    public static HeadsetDTO initDTOFromRequest(final HeadsetCreationRequest request) {
        if (request.getFrequency() < 20 || request.getFrequency() > 20000) {
            throw new IllegalArgumentException("'frequency' must be between 20 and 20000 inclusive");
        }
        return HeadsetDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .frequency(request.getFrequency())
                .connectivityType(ConnectivityType.valueOf(request.getConnectivityType().trim().toUpperCase()))
                .cableLength(request.getCableLength())
                .build();
    }
}