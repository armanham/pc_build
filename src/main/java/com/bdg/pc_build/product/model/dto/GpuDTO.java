package com.bdg.pc_build.product.model.dto;

public record GpuDTO(
         String name,
         Double price,
         String chipset,
         int memory,
         double coreClock,
         double boostClock,
         String color,
         double length,
         double tdp) {
}
