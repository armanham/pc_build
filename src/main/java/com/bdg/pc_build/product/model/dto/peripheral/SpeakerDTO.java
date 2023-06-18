package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.request.creation.peripheral.SpeakerCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class SpeakerDTO extends ProductDTO {

    Integer frequency;
    PowerSourceType powerSourceType;
    Double cableLength;
    String dimension;

    @Builder
    public SpeakerDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer frequency,
            final PowerSourceType powerSourceType,
            final Double cableLength,
            final String dimension
    ) {
        super(name, price, purchasedPrice, count);
        this.frequency = frequency;
        this.powerSourceType = powerSourceType;
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
                .powerSourceType(entity.getPowerSourceType())
                .cableLength(entity.getCableLength())
                .dimension(entity.getDimension())
                .build();
    }

    public static SpeakerDTO initDTOFromRequest(final SpeakerCreationRequest request) {
        if (Integer.parseInt(request.getFrequency()) < 20 || Integer.parseInt(request.getFrequency()) > 20000) {
            throw new IllegalArgumentException("'frequency' must be between 20 and 20000 inclusive");
        }
        return SpeakerDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .frequency(Integer.valueOf(request.getFrequency()))
                .powerSourceType(PowerSourceType.valueOf(request.getPowerSourceType().trim().toUpperCase()))
                .cableLength(Double.valueOf(request.getCableLength()))
                .dimension(request.getDimension())
                .build();
    }
}