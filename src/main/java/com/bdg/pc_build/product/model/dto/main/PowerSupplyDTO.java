package com.bdg.pc_build.product.model.dto.main;

import com.bdg.pc_build.product.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.enumerations.ModularType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.PowerSupply;
import com.bdg.pc_build.product.model.request.creation.main.PowerSupplyCreationRequest;
import lombok.Getter;

@Getter
public class PowerSupplyDTO extends ProductDTO {

    private final EfficiencyRating efficiencyRating;
    private final Integer wattage;
    private final ModularType modularType;
    private final Integer tdp;

    public PowerSupplyDTO(final PowerSupply entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.efficiencyRating = entity.getEfficiencyRating();
        this.wattage = entity.getWattage();
        this.modularType = entity.getModularType();
        this.tdp = entity.getTdp();
    }

    public PowerSupplyDTO(final PowerSupplyCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());
        this.efficiencyRating = EfficiencyRating.valueOf(request.getEfficiencyRating().trim().toUpperCase());
        this.wattage = request.getWattage();
        this.modularType = ModularType.valueOf(request.getModularType().trim().toUpperCase());
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "PowerSupplyDTO{" +
                "efficiencyRating=" + efficiencyRating +
                ", wattage=" + wattage +
                ", modularType=" + modularType +
                ", tdp=" + tdp +
                '}';
    }
}