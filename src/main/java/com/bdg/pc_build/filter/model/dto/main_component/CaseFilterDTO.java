package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CaseFilterRequest;
import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CaseFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Double minCpuCoolerHeight;
    private final Double maxCpuCoolerHeight;

    private final Double minGpuLength;
    private final Double maxGpuLength;

    private final Integer minPreInstalledFans;
    private final Integer maxPreInstalledFans;

    private final Set<TowerType> towerTypes;

    public CaseFilterDTO(final CaseFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minCpuCoolerHeight = request.minCpuCoolerHeight();
        this.maxCpuCoolerHeight = request.maxCpuCoolerHeight();
        if (this.minCpuCoolerHeight != null && this.maxCpuCoolerHeight != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCpuCoolerHeight, maxCpuCoolerHeight);
        }

        this.minGpuLength = request.minGpuLength();
        this.maxGpuLength = request.maxGpuLength();
        if (this.minGpuLength != null && this.maxGpuLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minGpuLength, maxGpuLength);
        }

        this.minPreInstalledFans = request.minPreInstalledFans();
        this.maxPreInstalledFans = request.maxPreInstalledFans();
        if (this.minPreInstalledFans != null && this.maxPreInstalledFans != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPreInstalledFans, maxPreInstalledFans);
        }

        if (request.towerTypes() != null && !request.towerTypes().isEmpty()) {
            this.towerTypes = request.towerTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> TowerType.toSetOfStrings().contains(s))
                    .map(TowerType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.towerTypes = null;
        }
    }
}