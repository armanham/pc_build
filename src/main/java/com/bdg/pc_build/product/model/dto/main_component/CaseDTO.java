package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.product.model.request.creation.main_component.CaseCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class CaseDTO extends ProductDTO {

    Double maxCpuCoolerHeight;
    Double maxGpuLength;
    Integer preInstalledFans;
    TowerType towerType;

    @Builder
    public CaseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Double maxCpuCoolerHeight,
            final Double maxGpuLength,
            final Integer preInstalledFans,
            final TowerType towerType
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCpuCoolerHeight = maxCpuCoolerHeight;
        this.maxGpuLength = maxGpuLength;
        this.preInstalledFans = preInstalledFans;
        this.towerType = towerType;
    }

    public static CaseDTO initDTOFromEntity(final aCase entity) {
        return CaseDTO.builder()
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
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .maxCpuCoolerHeight(Double.valueOf(request.getMaxCpuCoolerHeight()))
                .maxGpuLength(Double.valueOf(request.getMaxGpuLength()))
                .preInstalledFans(Integer.valueOf(request.getPreInstalledFans()))
                .towerType(TowerType.valueOf(request.getTowerType().trim().toUpperCase()))
                .build();
    }
}