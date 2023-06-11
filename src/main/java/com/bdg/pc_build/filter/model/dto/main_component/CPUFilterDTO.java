package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CPUFilterDTO {

    Double minPrice;
    Double maxPrice;
    Integer coreCount;
    Double coreClock;
    Double boostClock;
    Integer tdp;
    String integratedGraphics;
    SocketType socket;
}