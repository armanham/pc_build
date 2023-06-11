package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.product.model.enumerations.InternalHardDriveInterfaceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class InternalHardDriveFilterDTO {

    Double minPrice;
    Double maxPrice;
    InternalHardDriveInterfaceType type;
    Integer capacity;
    Integer tdp;
}