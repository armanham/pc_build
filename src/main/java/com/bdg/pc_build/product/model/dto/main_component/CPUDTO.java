package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPU;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of CPU for service layer.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class CPUDTO extends ProductDTO {

    Integer coreCount;
    Double coreClock;
    Double boostClock;
    Integer tdp;
    String integratedGraphics;
    SocketType socket;

    @Builder

    public CPUDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer coreCount,
            final Double coreClock,
            final Double boostClock,
            final Integer tdp,
            final String integratedGraphics,
            final SocketType socket
    ) {
        super(name, price, purchasedPrice, count);
        this.coreCount = coreCount;
        this.coreClock = coreClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.integratedGraphics = integratedGraphics;
        this.socket = socket;
    }

    public static CPUDTO initDTOFromEntity(final CPU entity){
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
                .socket(entity.getSocketType())
                .build();
    }

    public static CPUDTO initDTOFromRequest(final ProductRequest request){
        return CPUDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .coreCount(request.coreCount())
                .coreClock(request.coreClock())
                .boostClock(request.boostClock())
                .tdp(request.tdp())
                .integratedGraphics(request.integratedGraphics())
                .socket(request.socketType())
                .build();
    }
}