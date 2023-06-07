package com.bdg.pc_build.product.model.dto.main_component;

/**
 * @param name
 * @param price
 * @param socketCpu
 * @param formFactor
 * @param memoryMax
 * @param memorySlots
 * @param memoryType
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of Motherboard for service layer.
 */
public record MotherboardDTO(
        String name,
        Double price,
        String socketCpu,
        String formFactor,
        Integer memoryMax,
        Integer memorySlots,
        String memoryType,
        Integer tdp
) {
}