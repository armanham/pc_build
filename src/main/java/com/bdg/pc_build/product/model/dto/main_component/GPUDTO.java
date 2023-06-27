package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.request.creation.main_component.GPUCreationRequest;
import lombok.Builder;
import lombok.Getter;


@Getter
public class GPUDTO extends ProductDTO {

    private final GPUInterfaceType gpuInterfaceType;
    private final Integer memory;
    private final Double coreClock;
    private final Double boostClock;
    private final Double length;
    private final Integer tdp;

    @Builder
    public GPUDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final GPUInterfaceType gpuInterfaceType,
            final Integer memory,
            final Double coreClock,
            final Double boostClock,
            final Double length,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.gpuInterfaceType = gpuInterfaceType;
        this.memory = memory;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.length = length;
        this.tdp = tdp;
    }

    public static GPUDTO initDTOFromEntity(final GPU entity) {
        return GPUDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .gpuInterfaceType(entity.getGpuInterfaceType())
                .memory(entity.getMemory())
                .coreClock(entity.getCoreClock())
                .boostClock(entity.getBoostClock())
                .length(entity.getLength())
                .tdp(entity.getTdp())
                .build();
    }

    public static GPUDTO initDTOFromRequest(final GPUCreationRequest request) {
        if (request.getMemory() % 2 != 0) {
            throw new IllegalArgumentException("'memory' must be even number");
        }
        if (request.getCoreClock() >= request.getBoostClock()) {
            throw new IllegalArgumentException("'boostClock' must be greater than 'coreClock'");
        }
        return GPUDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase()))
                .memory(request.getMemory())
                .coreClock(request.getCoreClock())
                .boostClock(request.getBoostClock())
                .length(request.getLength())
                .tdp(request.getTdp())
                .build();
    }
}