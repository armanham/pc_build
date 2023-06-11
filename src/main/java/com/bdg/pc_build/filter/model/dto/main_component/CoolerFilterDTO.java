package com.bdg.pc_build.filter.model.dto.main_component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CoolerFilterDTO {

    Double minPrice;
    Double maxPrice;
    Integer tdp;

}