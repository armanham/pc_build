package com.bdg.pc_build.product.model.dto.main;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.Cooler;
import com.bdg.pc_build.product.model.request.creation.main.CoolerCreationRequest;
import lombok.Getter;

@Getter
public class CoolerDTO extends ProductDTO {

    private final Integer tdp;

    public CoolerDTO(final Cooler entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.tdp = entity.getTdp();
    }

    public CoolerDTO(final CoolerCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "CoolerDTO{" +
                "tdp=" + tdp +
                '}';
    }
}