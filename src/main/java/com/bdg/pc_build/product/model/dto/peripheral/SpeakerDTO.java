package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.request.creation.peripheral.SpeakerCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class SpeakerDTO extends ProductDTO {

    Integer frequency;
    String powerSource;
    Double cableLength;
    String dimension;

    @Builder
    public SpeakerDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final String powerSource,
            final Double cableLength,
            final String dimension
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.powerSource = powerSource;
        this.cableLength = cableLength;
        this.dimension = dimension;
    }

    public static SpeakerDTO initDTOFromEntity(final Speaker entity) {
        return SpeakerDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .frequency(entity.getFrequency())
                .powerSource(entity.getPowerSource())
                .cableLength(entity.getCableLength())
                .dimension(entity.getDimension())
                .build();
    }

    public static SpeakerDTO initDTOFromRequest(final SpeakerCreationRequest request) {
        return SpeakerDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .frequency(Integer.valueOf(request.getFrequency()))
                .powerSource(request.getPowerSource())
                .cableLength(Double.valueOf(request.getCableLength()))
                .dimension(request.getDimension())
                .build();
    }
}