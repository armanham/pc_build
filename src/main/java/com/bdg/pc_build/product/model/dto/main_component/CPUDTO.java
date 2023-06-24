package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.creation.main_component.CPUCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class CPUDTO extends ProductDTO {

    Integer coreCount;
    Double coreClock;
    Double boostClock;
    Integer tdp;
    String integratedGraphics;
    SocketType socketType;

    @Builder

    public CPUDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer coreCount,
            final Double coreClock,
            final Double boostClock,
            final Integer tdp,
            final String integratedGraphics,
            final SocketType socketType
    ) {
        super(id, name, price, purchasedPrice, count);
        this.coreCount = coreCount;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphics = integratedGraphics;
        this.socketType = socketType;
    }

    public static CPUDTO initDTOFromEntity(final CPU entity) {
        return CPUDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .coreCount(entity.getCoreCount())
                .coreClock(entity.getCoreClock())
                .boostClock(entity.getBoostClock())
                .tdp(entity.getTdp())
                .integratedGraphics(entity.getIntegratedGraphics())
                .socketType(entity.getSocketType())
                .build();
    }

    public static CPUDTO initDTOFromRequest(final CPUCreationRequest request) {
        if (Integer.parseInt(request.getCoreCount()) % 2 != 0) {
            throw new IllegalArgumentException("'coreCount' must be even number");
        }
        if (Double.parseDouble(request.getCoreClock()) >= Double.parseDouble(request.getBoostClock())) {
            throw new IllegalArgumentException("'boostClock' must be greater than 'coreClock'");
        }
        return CPUDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .coreCount(Integer.valueOf(request.getCoreCount()))
                .coreClock(Double.valueOf(request.getCoreClock()))
                .boostClock(Double.valueOf(request.getBoostClock()))
                .tdp(Integer.valueOf(request.getTdp()))
                .integratedGraphics(request.getIntegratedGraphics().trim())
                .socketType(SocketType.valueOf(request.getSocketType().trim().toUpperCase()))
                .build();
    }
}