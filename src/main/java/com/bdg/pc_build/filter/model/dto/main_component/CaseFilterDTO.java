package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.CaseFilterRequest;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CaseFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Double minCpuCoolerHeight;
    Double maxCpuCoolerHeight;

    Double minGpuLength;
    Double maxGpuLength;

    Integer minPreInstalledFans;
    Integer maxPreInstalledFans;

    final Set<TowerType> towerTypes;

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

        if (request.minCpuCoolerHeight() != null && !request.minCpuCoolerHeight().isBlank()) {
            this.minCpuCoolerHeight = Double.valueOf(request.minCpuCoolerHeight());
        }
        if (request.maxCpuCoolerHeight() != null && !request.maxCpuCoolerHeight().isBlank()) {
            this.maxCpuCoolerHeight = Double.valueOf(request.maxCpuCoolerHeight());
        }
        if (this.minCpuCoolerHeight != null && this.maxCpuCoolerHeight != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCpuCoolerHeight, maxCpuCoolerHeight);
        }

        if (request.minGpuLength() != null && !request.minGpuLength().isBlank()) {
            this.minGpuLength = Double.valueOf(request.minGpuLength());
        }
        if (request.maxGpuLength() != null && !request.maxGpuLength().isBlank()) {
            this.maxGpuLength = Double.valueOf(request.maxGpuLength());
        }
        if (this.minGpuLength != null && this.maxGpuLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minGpuLength, maxGpuLength);
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

        if (request.towerTypes() != null && !request.towerTypes().isEmpty()) {
            this.towerTypes = request.towerTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> TowerType.toListOfStrings().contains(s))
                    .map(TowerType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.towerTypes = null;
        }
    }
}