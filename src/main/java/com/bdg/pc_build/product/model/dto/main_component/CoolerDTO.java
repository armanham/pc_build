package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Cooler;
import com.bdg.pc_build.product.model.request.creation.main_component.CoolerCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CoolerDTO extends ProductDTO {

    private final Integer tdp;

    @Builder
    public CoolerDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.tdp = tdp;
    }

    public static CoolerDTO initDTOFromEntity(final Cooler entity) {
        return CoolerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .tdp(entity.getTdp())
                .build();
    }

    public static CoolerDTO initDTOFromRequest(final CoolerCreationRequest request) {
        return CoolerDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .tdp(request.getTdp())
                .build();
    }
}