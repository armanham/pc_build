package com.bdg.pc_build.product.model.dto.peripheral;

public record SpeakerDTO(
        String name,
        Double price,
        Integer count,
        Integer frequency,
        String powerSource,
        Double cableLength,
        String dimension
) {
}
