package com.bdg.pc_build.product.model.dto.main_component;

public record ExternalHardDriveDTO(
        String name,
        Double price,
        Integer count,
        String type,
        Integer capacity,
        Integer tdp
) {
}