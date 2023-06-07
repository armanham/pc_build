package com.bdg.pc_build.product.model.dto;

public record ExternalHardDriveDTO(
        String name,
        double price,
        String type,
        String interfaceType,
        int capacity,
        double pricePerGb,
        String color) {
}
