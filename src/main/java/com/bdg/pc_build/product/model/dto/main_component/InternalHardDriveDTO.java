package com.bdg.pc_build.product.model.dto.main_component;

public record InternalHardDriveDTO(
        String name,
        Double price,
        Integer count,
        Integer capacity,
        Integer tdp
) {
}
