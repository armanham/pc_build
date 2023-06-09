package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class GPUDTO extends ProductDTO {

    String chipset;
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
            final String chipset,
            final Integer memory,
            final Double coreClock,
            final Double boostClock,
            final Double length,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.chipset = chipset;
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
                .chipset(entity.getChipset())
                .memory(entity.getMemory())
                .coreClock(entity.getCoreClock())
                .boostClock(entity.getBoostClock())
                .length(entity.getLength())
                .tdp(entity.getTdp())
                .build();
    }

    public static GPUDTO initDTOFromRequest(final ProductRequest request){
        return GPUDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .chipset(request.chipset())
                .memory(request.memory())
                .coreClock(request.coreClock())
                .boostClock(request.boostClock())
                .length(request.length())
                .tdp(request.tdp())
                .build();
    }
}