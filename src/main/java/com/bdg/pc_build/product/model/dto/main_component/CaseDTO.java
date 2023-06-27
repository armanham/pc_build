package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.request.creation.main_component.CaseCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CaseDTO extends ProductDTO {

    private final Double maxCpuCoolerHeight;
    private final Double maxGpuLength;
    private final Integer preInstalledFans;
    private final TowerType towerType;

    @Builder
    public CaseDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double maxCpuCoolerHeight,
            final Double maxGpuLength,
            final Integer preInstalledFans,
            final TowerType towerType
    ) {
        super(id, name, price, purchasedPrice, count);
        this.maxCpuCoolerHeight = maxCpuCoolerHeight;
        this.maxGpuLength = maxGpuLength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType;
    }

    public static CaseDTO initDTOFromEntity(final aCase entity) {
        return CaseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .maxCpuCoolerHeight(entity.getMaxCpuCoolerHeight())
                .maxGpuLength(entity.getMaxGpuLength())
                .preInstalledFans(entity.getPreInstalledFans())
                .towerType(entity.getTowerType())
                .build();
    }

    public static CaseDTO initDTOFromRequest(final CaseCreationRequest request) {
        return CaseDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .maxCpuCoolerHeight(request.getMaxCpuCoolerHeight())
                .maxGpuLength(request.getMaxGpuLength())
                .preInstalledFans(request.getPreInstalledFans())
                .towerType(TowerType.valueOf(request.getTowerType().trim().toUpperCase()))
                .build();
    }
}