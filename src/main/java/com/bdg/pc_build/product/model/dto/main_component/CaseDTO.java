package com.bdg.pc_build.product.model.dto.main_component;

public record CaseDTO(
        String name,
        Double price,
        Integer maxCPUCoolerHeight,
        Double maxGPULength,
        Integer preInstalledFans,
        Boolean isATX
) {
}
