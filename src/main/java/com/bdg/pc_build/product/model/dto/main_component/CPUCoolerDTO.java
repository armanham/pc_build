package com.bdg.pc_build.product.model.dto.main_component;

public record CPUCoolerDTO(
        String name,
        Double price,
        Integer count,
        Integer fanRPM,
        String socket,
        Integer tdp
) {
}
