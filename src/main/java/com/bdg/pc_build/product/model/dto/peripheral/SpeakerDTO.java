package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.request.creation.peripheral.SpeakerCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SpeakerDTO extends ProductDTO {

    private final Integer frequency;
    private final PowerSourceType powerSourceType;
    private final Double cableLength;
    private final String dimension;

    @Builder
    public SpeakerDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final PowerSourceType powerSourceType,
            final Double cableLength,
            final String dimension
    ) {
        super(id, name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.powerSourceType = powerSourceType;
        this.cableLength = cableLength;
        this.dimension = dimension;
    }

    public static SpeakerDTO initDTOFromEntity(final Speaker entity) {
        return SpeakerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .frequency(entity.getFrequency())
                .powerSourceType(entity.getPowerSourceType())
                .cableLength(entity.getCableLength())
                .dimension(entity.getDimension())
                .build();
    }

    public static SpeakerDTO initDTOFromRequest(final SpeakerCreationRequest request) {
        if (request.getFrequency() < 20 || request.getFrequency() > 20000) {
            throw new IllegalArgumentException("'frequency' must be between 20 and 20000 inclusive");
        }
        return SpeakerDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .frequency(request.getFrequency())
                .powerSourceType(PowerSourceType.valueOf(request.getPowerSourceType().trim().toUpperCase()))
                .cableLength(request.getCableLength())
                .dimension(request.getDimension())
                .build();
    }
}