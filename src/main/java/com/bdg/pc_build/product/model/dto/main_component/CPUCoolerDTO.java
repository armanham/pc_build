package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.creation.main_component.CPUCoolerCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class CPUCoolerDTO extends ProductDTO {

    Integer fanRpm;
    SocketType socketType;
    Integer tdp;

    @Builder
    public CPUCoolerDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer fanRpm,
            final SocketType socketType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.fanRpm = fanRpm;
        this.socketType = socketType;
        this.tdp = tdp;
    }

    public static CPUCoolerDTO initDTOFromEntity(CPUCooler entity) {
        return CPUCoolerDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .fanRpm(entity.getFanRpm())
                .socketType(entity.getSocketType())
                .tdp(entity.getTdp())
                .build();
    }

    public static CPUCoolerDTO initDTOFromRequest(final CPUCoolerCreationRequest request) {
        return CPUCoolerDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .fanRpm(Integer.valueOf(request.getFanRpm()))
                .socketType(SocketType.valueOf(request.getSocketType()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}