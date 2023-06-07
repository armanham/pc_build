package com.bdg.pc_build.product.model.dto;

/**
 * @param name
 * @param price
 * @param coreCount
 * @param coreClock
 * @param boostClock
 * @param tdp
 * @param integratedGraphics
 * @param socket
 * @param smt
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of CPU for service layer.
 */
public record CpuDTO(
        String name,
        Double price,
        Integer coreCount,
        Integer coreClock,
        Integer boostClock,
        Integer tdp,
        String integratedGraphics,
        String socket,
        String smt
) {
    //equals and hashcode
}
