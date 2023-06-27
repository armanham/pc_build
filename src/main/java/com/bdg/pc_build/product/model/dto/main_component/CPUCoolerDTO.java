package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.request.creation.main_component.CPUCoolerCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CPUCoolerDTO extends ProductDTO {

    private final Integer fanRpm;
    private final SocketType socketType;
    private final Integer tdp;

    @Builder
    public CPUCoolerDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer fanRpm,
            final SocketType socketType,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.fanRpm = fanRpm;
        this.socketType = socketType;
        this.tdp = tdp;
    }

    public static CPUCoolerDTO initDTOFromEntity(CPUCooler entity) {
        return CPUCoolerDTO.builder()
                .id(entity.getId())
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
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .fanRpm(request.getFanRpm())
                .socketType(SocketType.valueOf(request.getSocketType().trim().toUpperCase()))
                .tdp(request.getTdp())
                .build();
    }
}