package com.bdg.pc_build.product.model.dto.main_component;


/**
 * @param name
 * @param rating
 * @param ratingCount
 * @param priceUSD
 * @param speed
 * @param modules
 * @param priceGB
 * @param color
 * @param firstWordLatency
 * @param casLatency
 * @param rgb
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of RAM for service layer.
 */
public record RAMDTO(
        String name,
        Double price,
        Integer count,
        Integer speed,
        Integer countOfRAM,
        Integer GBOfRAM,
        Integer tdp
) {
}
