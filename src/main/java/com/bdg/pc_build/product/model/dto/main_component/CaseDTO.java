package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class CaseDTO extends ProductDTO {

    Integer maxCPUCoolerHeight;
    Double maxGPULength;
    Integer preInstalledFans;
    TowerType towerType;

    @Builder
    public CaseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer maxCPUCoolerHeight,
            final Double maxGPULength,
            final Integer preInstalledFans,
            final TowerType towerType
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCPUCoolerHeight = maxCPUCoolerHeight;
        this.maxGPULength = maxGPULength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType;
    }

    public static CaseDTO initDTOFromEntity(final aCase entity) {
        return CaseDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .maxCPUCoolerHeight(entity.getMaxCPUCoolerHeight())
                .maxGPULength(entity.getMaxGPULength())
                .preInstalledFans(entity.getPreInstalledFans())
                .towerType(entity.getTowerType())
                .build();
    }

    public static CaseDTO initDTOFromRequest(final ProductRequest request) {
        return CaseDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .maxCPUCoolerHeight(request.maxCPUCoolerHeight())
                .maxGPULength(request.maxGPULength())
                .preInstalledFans(request.preInstalledFans())
                .towerType(request.towerType())
                .build();
    }
}