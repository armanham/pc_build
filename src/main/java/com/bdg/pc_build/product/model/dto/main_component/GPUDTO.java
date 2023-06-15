package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.request.creation.main_component.GPUCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class GPUDTO extends ProductDTO {

    GPUInterfaceType gpuInterfaceType;
    Integer memory;
    Double coreClock;
    Double boostClock;
    Double length;
    Integer tdp;

    @Builder
    public GPUDTO(
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
        super(name, price, purchasedPrice, count);
        this.gpuInterfaceType = gpuInterfaceType;
        this.memory = memory;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.length = length;
        this.tdp = tdp;
    }

    public static GPUDTO initDTOFromEntity(final GPU entity) {
        return GPUDTO.builder()
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
        return GPUDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase()))
                .memory(Integer.valueOf(request.getMemory()))
                .coreClock(Double.valueOf(request.getCoreClock()))
                .boostClock(Double.valueOf(request.getBoostClock()))
                .length(Double.valueOf(request.getLength()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}