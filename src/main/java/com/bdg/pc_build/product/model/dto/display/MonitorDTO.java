package com.bdg.pc_build.product.model.dto.display;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

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


    public static MonitorDTO initDTOFromRequest(final ProductRequest request){
        return MonitorDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .screenType(request.screenType())
                .refreshRate(request.refreshRate())
                .screenSize(request.screenSize())
                .build();
    }
}