package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class CPUCoolerDTO extends ProductDTO {

    Integer fanRPM;
    SocketType socketType;
    Integer tdp;

    @Builder
    public CPUCoolerDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer fanRPM,
            final SocketType socketType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.fanRPM = fanRPM;
        this.socketType = socketType;
        this.tdp = tdp;
    }

    public static CPUCoolerDTO initDTOFromEntity(CPUCooler entity) {
        return CPUCoolerDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .fanRPM(entity.getFanRPM())
                .socketType(entity.getSocketType())
                .tdp(entity.getTdp())
                .build();
    }

    public static CPUCoolerDTO initDTOFromRequest(final ProductRequest request) {
        return CPUCoolerDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.purchasedPrice()))
                .fanRPM(Integer.valueOf(request.fanRPM()))
                .socketType(SocketType.valueOf(request.socket()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}
