package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Mouse;
import com.bdg.pc_build.product.model.request.creation.peripheral.MouseCreationRequest;
import lombok.Getter;

@Getter
public class MouseDTO extends ProductDTO {

    private final ConnectivityType connectivityType;
    private final Integer maxResolution;
    private final Double cableLength;
    private final Double weight;

    public MouseDTO(final Mouse entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.connectivityType = entity.getConnectivityType();
        this.maxResolution = entity.getMaxResolution();
        this.cableLength = entity.getCableLength();
        this.weight = entity.getWeight();
    }

    public MouseDTO(final MouseCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.connectivityType = ConnectivityType.valueOf(request.getConnectivityType().trim().toUpperCase());
        this.maxResolution = request.getMaxResolution();
        this.cableLength = request.getCableLength();
        this.weight = request.getWeight();
    }
}