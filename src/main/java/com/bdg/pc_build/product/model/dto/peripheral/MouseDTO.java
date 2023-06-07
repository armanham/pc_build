package com.bdg.pc_build.product.model.dto.peripheral;

public record MouseDTO(
        String name,
        Double price,
        Integer count,
        String type,
        Integer maxResolution,
        Double cableLength,
        Double weight
) {
}
