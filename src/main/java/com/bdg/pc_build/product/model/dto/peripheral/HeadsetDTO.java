package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Headset;
import com.bdg.pc_build.product.model.request.creation.peripheral.HeadsetCreationRequest;
import lombok.Getter;

@Getter
public class HeadsetDTO extends ProductDTO {

    private final Integer frequency;
    private final ConnectivityType connectivityType;
    private final Double cableLength;

    public HeadsetDTO(final Headset entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.frequency = entity.getFrequency();
        this.connectivityType = entity.getConnectivityType();
        this.cableLength = entity.getCableLength();
    }

    public HeadsetDTO(final HeadsetCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if (request.getFrequency() < 20 || request.getFrequency() > 20000) {
            throw new IllegalArgumentException("Frequency of Headset must be between 20 and 20000 inclusive: ");
        }
        this.frequency = request.getFrequency();
        this.connectivityType = ConnectivityType.valueOf(request.getConnectivityType().trim().toUpperCase());
        this.cableLength = request.getCableLength();
    }
}