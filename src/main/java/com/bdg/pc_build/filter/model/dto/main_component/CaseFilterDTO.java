package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CaseFilterRequest;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CaseFilterDTO {

    Double minPrice;
    Double maxPrice;

    Double minCPUCoolerHeight;
    Double maxCPUCoolerHeight;

    Double minGPULength;
    Double maxGPULength;

    Integer minPreInstalledFans;
    Integer maxPreInstalledFans;

    TowerType towerType;

    public CaseFilterDTO(final CaseFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
    }
}