package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.MotherboardFilterRequest;
import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MotherboardFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    private Integer minMemory;
    private Integer maxMemory;

    private Integer minMemorySlots;
    private Integer maxMemorySlots;

    private Integer minTdp;
    private Integer maxTdp;

    private final Set<Boolean> isM2;

    private final Set<DDRType> ddrTypes;
    private final Set<GPUInterfaceType> gpuInterfaceTypes;
    private final Set<SocketType> socketTypes;
    private final Set<ATXType> atxTypes;

    public MotherboardFilterDTO(final MotherboardFilterRequest request) {
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        if (request.minMemory() != null && !request.minMemory().isBlank()) {
            this.minMemory = Integer.valueOf(request.minMemory());
        }
        if (request.maxMemory() != null && !request.maxMemory().isBlank()) {
            this.maxMemory = Integer.valueOf(request.maxMemory());
        }
        if (this.minMemory != null && this.maxMemory != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(maxMemory, maxMemory);
        }

        if (request.minMemorySlots() != null && !request.minMemorySlots().isBlank()) {
            this.minMemorySlots = Integer.valueOf(request.minMemorySlots());
        }
        if (request.maxMemorySlots() != null && !request.maxMemorySlots().isBlank()) {
            this.maxMemorySlots = Integer.valueOf(request.maxMemorySlots());
        }
        if (this.minMemorySlots != null && this.maxMemorySlots != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minMemorySlots, maxMemorySlots);
        }

        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.isM2() != null && !request.isM2().isEmpty()) {
            this.isM2 = request.isM2()
                    .stream()
                    .map(String::trim)
                    .filter(s -> s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"))
                    .map(Boolean::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.isM2 = null;
        }

        if (request.ddrTypes() != null && !request.ddrTypes().isEmpty()) {
            this.ddrTypes = request.ddrTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> DDRType.toListOfStrings().contains(s))
                    .map(DDRType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.ddrTypes = null;
        }

        if (request.gpuInterfaceTypes() != null && !request.gpuInterfaceTypes().isEmpty()) {
            this.gpuInterfaceTypes = request.gpuInterfaceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> GPUInterfaceType.toListOfStrings().contains(s))
                    .map(GPUInterfaceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.gpuInterfaceTypes = null;
        }

        if (request.socketTypes() != null && !request.socketTypes().isEmpty()) {
            this.socketTypes = request.socketTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> SocketType.toListOfStrings().contains(s))
                    .map(SocketType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.socketTypes = null;
        }

        if (request.atxTypes() != null && !request.atxTypes().isEmpty()) {
            this.atxTypes = request.atxTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> ATXType.toListOfStrings().contains(s))
                    .map(ATXType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.atxTypes = null;
        }
    }
}