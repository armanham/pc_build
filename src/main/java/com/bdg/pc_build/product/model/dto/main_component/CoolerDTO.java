package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Cooler;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CoolerDTO extends ProductDTO {
    Integer tdp;

    @Builder
    public CoolerDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.tdp = tdp;
    }

    public static CoolerDTO initDTOFromEntity(final Cooler entity) {
        return CoolerDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .tdp(entity.getTdp())
                .build();
    }

    public static CoolerDTO initDTOFromRequest(final ProductRequest request){
        return CoolerDTO.builder()
                .name(request.getName())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .tdp(request.getTdp())
                .build();
    }
}
