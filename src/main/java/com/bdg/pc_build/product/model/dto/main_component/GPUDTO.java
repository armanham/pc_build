package com.bdg.pc_build.product.model.dto.main_component;

public record GPUDTO(
        String name,
        Double price,
        Integer count,
        String chipset,
        Integer memory,
        Double coreClock,
        Double boostClock,
        Double length,
        Integer tdp
) {
}