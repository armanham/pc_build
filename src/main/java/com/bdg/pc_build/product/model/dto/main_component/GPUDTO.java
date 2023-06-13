package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
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

    public static GPUDTO initDTOFromRequest(final ProductRequest request) {
        return GPUDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.gpuInterfaceType()))
                .memory(Integer.valueOf(request.memory()))
                .coreClock(Double.valueOf(request.coreClock()))
                .boostClock(Double.valueOf(request.boostClock()))
                .length(Double.valueOf(request.length()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}