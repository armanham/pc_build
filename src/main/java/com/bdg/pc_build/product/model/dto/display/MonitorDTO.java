package com.bdg.pc_build.product.model.dto.display;

public record MonitorDTO(
        String name,
        Double price,
        Double screenSize,
        Integer refreshRate,
        String screenType
) {
}
