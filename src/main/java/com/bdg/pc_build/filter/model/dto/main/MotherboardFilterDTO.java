package com.bdg.pc_build.filter.model.dto.main;

import com.bdg.pc_build.filter.model.request.main.MotherboardFilterRequest;
import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MotherboardFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minMemory;
    private final Integer maxMemory;

    private final Integer minMemorySlots;
    private final Integer maxMemorySlots;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<Boolean> isM2;

    private final Set<DDRType> ddrTypes;
    private final Set<GPUInterfaceType> gpuInterfaceTypes;
    private final Set<SocketType> socketTypes;
    private final Set<ATXType> atxTypes;

    public MotherboardFilterDTO(final MotherboardFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minMemory = request.minMemory();
        this.maxMemory = request.maxMemory();
        if (this.minMemory != null && this.maxMemory != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(maxMemory, maxMemory);
        }

        this.minMemorySlots = request.minMemorySlots();
        this.maxMemorySlots = request.maxMemorySlots();
        if (this.minMemorySlots != null && this.maxMemorySlots != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minMemorySlots, maxMemorySlots);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
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
                    .filter(s -> DDRType.toSetOfStrings().contains(s))
                    .map(DDRType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.ddrTypes = null;
        }

        if (request.gpuInterfaceTypes() != null && !request.gpuInterfaceTypes().isEmpty()) {
            this.gpuInterfaceTypes = request.gpuInterfaceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> GPUInterfaceType.toSetOfStrings().contains(s))
                    .map(GPUInterfaceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.gpuInterfaceTypes = null;
        }

        if (request.socketTypes() != null && !request.socketTypes().isEmpty()) {
            this.socketTypes = request.socketTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> SocketType.toSetOfStrings().contains(s))
                    .map(SocketType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.socketTypes = null;
        }

        if (request.atxTypes() != null && !request.atxTypes().isEmpty()) {
            this.atxTypes = request.atxTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> ATXType.toSetOfStrings().contains(s))
                    .map(ATXType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.atxTypes = null;
        }
    }
}