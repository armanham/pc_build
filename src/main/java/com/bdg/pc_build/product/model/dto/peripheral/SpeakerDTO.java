package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Speaker;
import com.bdg.pc_build.product.model.request.creation.peripheral.SpeakerCreationRequest;
import lombok.Getter;

@Getter
public class SpeakerDTO extends ProductDTO {

    private final Integer frequency;
    private final PowerSourceType powerSourceType;
    private final Double cableLength;
    private final String dimension;

    public SpeakerDTO(final Speaker entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.frequency = entity.getFrequency();
        this.powerSourceType = entity.getPowerSourceType();
        this.cableLength = entity.getCableLength();
        this.dimension = entity.getDimension();
    }

    public SpeakerDTO(final SpeakerCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if (request.getFrequency() < 20 || request.getFrequency() > 20000) {
            throw new IllegalArgumentException("Frequency of Speaker must be between 20 and 20000 inclusive: ");
        }
        this.frequency = request.getFrequency();
        this.powerSourceType = PowerSourceType.valueOf(request.getPowerSourceType().trim().toUpperCase());
        this.cableLength = request.getCableLength();
        this.dimension = request.getDimension();
    }
}