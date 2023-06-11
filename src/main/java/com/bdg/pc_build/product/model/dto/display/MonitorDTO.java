package com.bdg.pc_build.product.model.dto.display;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class MonitorDTO extends ProductDTO {

    Double screenSize;
    Integer refreshRate;
    String screenType;

    @Builder
    public MonitorDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double screenSize,
            final Integer refreshRate,
            final String screenType
    ) {
        super(name, price, purchasedPrice, count);
        ValidationUtil.validatePositivityOfNumber(MonitorDTO.class, "screenSize", screenSize);
        ValidationUtil.validatePositivityOfNumber(MonitorDTO.class, "refreshRate", refreshRate);
        // ValidationUtil.validateNonBlankAndNonNullString(MonitorDTO.class, "screenType", screenType);
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

    public static MonitorDTO initDTOFromRequest(final ProductRequest request) {
        return MonitorDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .screenType(request.screenType())
                .refreshRate(Integer.valueOf(request.refreshRate()))
                .screenSize(Double.valueOf(request.screenSize()))
                .build();
    }
}