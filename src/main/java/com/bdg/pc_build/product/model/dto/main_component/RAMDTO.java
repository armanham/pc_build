package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.request.creation.main_component.RAMCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RAMDTO extends ProductDTO {

    private final DDRType ddrType;
    private final Integer countOfRam;
    private final Integer gbOfRam;
    private final Integer tdp;

    @Builder
    public RAMDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final DDRType ddrType,
            final Integer countOfRam,
            final Integer gbOfRam,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.ddrType = ddrType;
        this.countOfRam = countOfRam;
        this.gbOfRam = gbOfRam;
        this.tdp = tdp;
    }

    public static RAMDTO initDTOFromEntity(final RAM entity) {
        return RAMDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .ddrType(entity.getDdrType())
                .countOfRam(entity.getCountOfRam())
                .gbOfRam(entity.getGbOfRam())
                .tdp(entity.getTdp())
                .build();
    }

    public static RAMDTO initDTOFromRequest(final RAMCreationRequest request) {
        if ((request.getGbOfRam() & request.getGbOfRam() - 1) != 0) {
            throw new IllegalArgumentException("'memoryMax' must be power of two");
        }
        return RAMDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .ddrType(DDRType.valueOf(request.getDdrType().trim().toUpperCase()))
                .countOfRam(request.getCountOfRam())
                .gbOfRam(request.getGbOfRam())
                .tdp(request.getTdp())
                .build();
    }
}