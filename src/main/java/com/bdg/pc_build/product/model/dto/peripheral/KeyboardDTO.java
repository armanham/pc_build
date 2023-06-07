package com.bdg.pc_build.product.model.dto.peripheral;

public record KeyboardDTO(
        String name,
        Double price,
        String keyboardClass,
        Double cableLength,
        String dimension,
        Double weight
) {
}
