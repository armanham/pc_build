package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.Case;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CaseDTO extends ProductDTO {

    Integer maxCPUCoolerHeight;
    Double maxGPULength;
    Integer preInstalledFans;
    Boolean isATX;

    @Builder
    public CaseDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer maxCPUCoolerHeight,
            final Double maxGPULength,
            final Integer preInstalledFans,
            final Boolean isATX
    ) {
        super(name, price, purchasedPrice, count);
        this.maxCPUCoolerHeight = maxCPUCoolerHeight;
        this.maxGPULength = maxGPULength;
        this.preInstalledFans = preInstalledFans;
        this.isATX = isATX;
    }

    public static CaseDTO initDTOFromEntity(final Case entity) {
        return CaseDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .maxCPUCoolerHeight(entity.getMaxCPUCoolerHeight())
                .maxGPULength(entity.getMaxGPULength())
                .isATX(entity.getIsATX())
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
                .isATX(request.isATX())
                .build();
    }
}