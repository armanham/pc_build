package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class GPUFilterDTO {

    Double minPrice;
    Double maxPrice;
    GPUInterfaceType gpuInterfaceType;
    Integer memory;
    Double coreClock;
    Double boostClock;
    Double length;
    Integer tdp;
}