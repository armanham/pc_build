package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.CPUCooler;
import com.bdg.pc_build.product.model.request.creation.main_component.CPUCoolerCreationRequest;
import lombok.Getter;

@Getter
public class CPUCoolerDTO extends ProductDTO {

    private final Integer fanRpm;
    private final SocketType socketType;
    private final Integer tdp;

    public CPUCoolerDTO(final CPUCooler entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.fanRpm = entity.getFanRpm();
        this.socketType = entity.getSocketType();
        this.tdp = entity.getTdp();
    }

    public CPUCoolerDTO(final CPUCoolerCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.fanRpm = request.getFanRpm();
        this.socketType = SocketType.valueOf(request.getSocketType().trim().toUpperCase());
        this.tdp = request.getTdp();
    }
}