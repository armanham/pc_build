package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import com.bdg.pc_build.product.model.request.creation.main_component.CaseCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class CaseDTO extends ProductDTO {

    Double maxCPUCoolerHeight;
    Double maxGPULength;
    Integer preInstalledFans;
    TowerType towerType;

    @Builder
    public CaseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double maxCPUCoolerHeight,
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

    public static CaseDTO initDTOFromRequest(final CaseCreationRequest request) {
        return CaseDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .maxCPUCoolerHeight(Double.valueOf(request.getMaxCPUCoolerHeight()))
                .maxGPULength(Double.valueOf(request.getMaxGPULength()))
                .preInstalledFans(Integer.valueOf(request.getPreInstalledFans()))
                .towerType(TowerType.valueOf(request.getTowerType()))
                .build();
    }
}