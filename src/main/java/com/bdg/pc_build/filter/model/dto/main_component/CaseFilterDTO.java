package com.bdg.pc_build.filter.model.dto.main_component;

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
    Double maxCPUCoolerHeight;
    Double maxGPULength;
    Integer preInstalledFans;
    TowerType towerType;

}