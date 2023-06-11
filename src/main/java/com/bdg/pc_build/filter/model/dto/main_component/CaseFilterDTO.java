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

    String name;

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
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minCPUCoolerHeight() != null && !request.minCPUCoolerHeight().isBlank()) {
            this.minCPUCoolerHeight = Double.valueOf(request.minCPUCoolerHeight());
        }
        if (request.maxCPUCoolerHeight() != null && !request.maxCPUCoolerHeight().isBlank()) {
            this.maxCPUCoolerHeight = Double.valueOf(request.maxCPUCoolerHeight());
        }
        if (request.minGPULength() != null && !request.minGPULength().isBlank()) {
            this.minGPULength = Double.valueOf(request.minGPULength());
        }
        if (request.maxGPULength() != null && !request.maxGPULength().isBlank()) {
            this.maxGPULength = Double.valueOf(request.maxGPULength());
        }
        if (request.minPreInstalledFans() != null && !request.minPreInstalledFans().isBlank()) {
            this.minPreInstalledFans = Integer.valueOf(request.minPreInstalledFans());
        }
        if (request.maxPreInstalledFans() != null && !request.maxPreInstalledFans().isBlank()) {
            this.maxPreInstalledFans = Integer.valueOf(request.maxPreInstalledFans());
        }
        //TODO TowerType
    }
}