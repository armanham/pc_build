package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Keyboard;
import com.bdg.pc_build.product.model.request.creation.peripheral.KeyboardCreationRequest;
import lombok.Getter;

@Getter
public class KeyboardDTO extends ProductDTO {

    private final ConnectivityType connectivityType;
    private final Double cableLength;
    private final String dimension;
    private final Double weight;

    public KeyboardDTO(final Keyboard entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.connectivityType = entity.getConnectivityType();
        this.cableLength = entity.getCableLength();
        this.dimension = entity.getDimension();
        this.weight = entity.getWeight();
    }

    public KeyboardDTO(final KeyboardCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.connectivityType = ConnectivityType.valueOf(request.getConnectivityType().trim().toUpperCase());
        this.cableLength = request.getCableLength();
        this.dimension = request.getDimension();
        this.weight = request.getWeight();
    }
}