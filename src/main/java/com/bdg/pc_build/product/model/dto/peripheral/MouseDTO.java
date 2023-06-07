package com.bdg.pc_build.product.model.dto.peripheral;

public record MouseDTO(
        String name,
        Double price,
        String type,
        Integer maxResolution,
        Double cableLength,
        Double weight
) {
}
