package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.CaseFilterRequest;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CaseFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Double minCPUCoolerHeight;
    Double maxCPUCoolerHeight;

    Double minGPULength;
    Double maxGPULength;

    Integer minPreInstalledFans;
    Integer maxPreInstalledFans;

    List<TowerType> towerTypes;

    public CaseFilterDTO(final CaseFilterRequest request) {
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        if (request.minCPUCoolerHeight() != null && !request.minCPUCoolerHeight().isBlank()) {
            this.minCPUCoolerHeight = Double.valueOf(request.minCPUCoolerHeight());
        }
        if (request.maxCPUCoolerHeight() != null && !request.maxCPUCoolerHeight().isBlank()) {
            this.maxCPUCoolerHeight = Double.valueOf(request.maxCPUCoolerHeight());
        }
        if (this.minCPUCoolerHeight != null && this.maxCPUCoolerHeight != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCPUCoolerHeight, maxCPUCoolerHeight);
        }

        if (request.minGPULength() != null && !request.minGPULength().isBlank()) {
            this.minGPULength = Double.valueOf(request.minGPULength());
        }
        if (request.maxGPULength() != null && !request.maxGPULength().isBlank()) {
            this.maxGPULength = Double.valueOf(request.maxGPULength());
        }
        if (this.minGPULength != null && this.maxGPULength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minGPULength, maxGPULength);
        }

        if (request.minPreInstalledFans() != null && !request.minPreInstalledFans().isBlank()) {
            this.minPreInstalledFans = Integer.valueOf(request.minPreInstalledFans());
        }
        if (request.maxPreInstalledFans() != null && !request.maxPreInstalledFans().isBlank()) {
            this.maxPreInstalledFans = Integer.valueOf(request.maxPreInstalledFans());
        }
        if (this.minPreInstalledFans != null && this.maxPreInstalledFans != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPreInstalledFans, maxPreInstalledFans);
        }
        //TODO TowerType
    }
}