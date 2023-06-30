package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.TowerType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.aCase;
import com.bdg.pc_build.product.model.request.creation.main_component.CaseCreationRequest;
import lombok.Getter;

@Getter
public class CaseDTO extends ProductDTO {

    private final Double maxCpuCoolerHeight;
    private final Double maxGpuLength;
    private final Integer preInstalledFans;
    private final TowerType towerType;

    public CaseDTO(final aCase entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.maxCpuCoolerHeight = entity.getMaxCpuCoolerHeight();
        this.maxGpuLength = entity.getMaxGpuLength();
        this.preInstalledFans = entity.getPreInstalledFans();
        this.towerType = entity.getTowerType();
    }

    public CaseDTO(final CaseCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.maxCpuCoolerHeight = request.getMaxCpuCoolerHeight();
        this.maxGpuLength = request.getMaxGpuLength();
        this.preInstalledFans = request.getPreInstalledFans();
        this.towerType = TowerType.valueOf(request.getTowerType().trim().toUpperCase());
    }

    @Override
    public String toString() {
        return "CaseDTO{" +
                "maxCpuCoolerHeight=" + maxCpuCoolerHeight +
                ", maxGpuLength=" + maxGpuLength +
                ", preInstalledFans=" + preInstalledFans +
                ", towerType=" + towerType +
                '}';
    }
}