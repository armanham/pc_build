package com.bdg.pc_build.product.model.dto.main_component;

public record ExternalHardDriveDTO(
        String name,
        Double price,
        String type,
        Integer capacity,
        Integer tdp
) {
}