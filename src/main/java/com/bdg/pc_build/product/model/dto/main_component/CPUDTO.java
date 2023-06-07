package com.bdg.pc_build.product.model.dto.main_component;

/**
 * @param name
 * @param price
 * @param coreCount
 * @param coreClock
 * @param boostClock
 * @param tdp
 * @param integratedGraphics
 * @param socket
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of CPU for service layer.
 */
public record CPUDTO(
        String name,
        Double price,
        Integer count,
        Integer coreCount,
        Integer coreClock,
        Integer boostClock,
        Integer tdp,
        String integratedGraphics,
        String socket
) {
    //equals and hashcode
}