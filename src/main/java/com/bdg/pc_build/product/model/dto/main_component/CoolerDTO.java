package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.main_component.Cooler;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
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
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .tdp(request.tdp())
                .build();
    }
}