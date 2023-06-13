package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.Monitor;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.model.request.creation.peripheral.MonitorCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class MonitorDTO extends ProductDTO {

    Double screenSize;
    Integer refreshRate;
    MonitorScreenType screenType;

    @Builder
    public MonitorDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double screenSize,
            final Integer refreshRate,
            final MonitorScreenType screenType
    ) {
        super(name, price, purchasedPrice, count);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
    }

    public static MonitorDTO initDTOFromEntity(final Monitor entity) {
        return MonitorDTO.builder()
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
        return MonitorDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .screenType(MonitorScreenType.valueOf(request.getScreenType()))
                .refreshRate(Integer.valueOf(request.getRefreshRate()))
                .screenSize(Double.valueOf(request.getScreenSize()))
                .build();
    }
}