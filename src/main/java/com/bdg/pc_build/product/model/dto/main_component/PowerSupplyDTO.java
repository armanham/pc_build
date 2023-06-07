package com.bdg.pc_build.product.model.dto.main_component;

/**
 * @param name
 * @param efficiencyRating
 * @param wattage
 * @param modular
 * @param tdp
 * @author Arman Hakhverdyan
 * <p>
 * DTO (Data Transfer Object) representing a power supply.
 */
public record PowerSupplyDTO(
        String name,
        Double price,
        Integer count,
        String efficiencyRating,
        Integer wattage,
        Boolean modular,
        Integer tdp
) {
}