package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.request.creation.peripheral.MonitorCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MonitorDTO extends ProductDTO {

    private final Double screenSize;
    private final Integer refreshRate;
    private final MonitorScreenType screenType;

    @Builder
    public MonitorDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double screenSize,
            final Integer refreshRate,
            final MonitorScreenType screenType
    ) {
        super(id, name, price, purchasedPrice, count);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
    }

    public static MonitorDTO initDTOFromEntity(final Monitor entity) {
        return MonitorDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .screenType(entity.getScreenType())
                .refreshRate(entity.getRefreshRate())
                .screenSize(entity.getScreenSize())
                .build();
    }

    public static MonitorDTO initDTOFromRequest(final MonitorCreationRequest request) {
        if (request.getRefreshRate() > 30) {
            throw new IllegalArgumentException("'refreshRate' must be less or equal than 30");
        }
        return MonitorDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .screenType(MonitorScreenType.valueOf(request.getScreenType().trim().toUpperCase()))
                .refreshRate(request.getRefreshRate())
                .screenSize(request.getScreenSize())
                .build();
    }
}