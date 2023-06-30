package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.request.creation.peripheral.MonitorCreationRequest;
import lombok.Getter;

@Getter
public class MonitorDTO extends ProductDTO {

    private final Double screenSize;
    private final Integer refreshRate;
    private final MonitorScreenType screenType;

    public MonitorDTO(final Monitor entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.screenSize = entity.getScreenSize();
        this.refreshRate = entity.getRefreshRate();
        this.screenType = entity.getScreenType();
    }

    public MonitorDTO(final MonitorCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if (request.getRefreshRate() > 30) {
            throw new IllegalArgumentException("Refresh rate of Monitor must be less or equal than 30: ");
        }
        this.screenSize = request.getScreenSize();
        this.refreshRate = request.getRefreshRate();
        this.screenType = MonitorScreenType.valueOf(request.getScreenType().trim().toUpperCase());
    }

    @Override
    public String toString() {
        return "MonitorDTO{" +
                "screenSize=" + screenSize +
                ", refreshRate=" + refreshRate +
                ", screenType=" + screenType +
                '}';
    }
}