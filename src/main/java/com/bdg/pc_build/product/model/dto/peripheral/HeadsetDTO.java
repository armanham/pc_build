package com.bdg.pc_build.product.model.dto.peripheral;

public record HeadsetDTO(
        String name,
        Double price,
        Integer frequency,
        String connectivity,
        Double tableLength
) {
}
