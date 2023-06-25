package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.enumerations.ModularType;
import com.bdg.pc_build.product.model.request.creation.main_component.PowerSupplyCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class PowerSupplyDTO extends ProductDTO {

    EfficiencyRating efficiencyRating;
    Integer wattage;
    ModularType modularType;
    Integer tdp;

    @Builder
    public PowerSupplyDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final EfficiencyRating efficiencyRating,
            final Integer wattage,
            final ModularType modularType,
            final Integer tdp) {
        super(id, name, price, purchasedPrice, count);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.modularType = modularType;
        this.tdp = tdp;
    }

    public static PowerSupplyDTO initDTOFromEntity(final PowerSupply entity) {
        return PowerSupplyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .efficiencyRating(entity.getEfficiencyRating())
                .wattage(entity.getWattage())
                .modularType(entity.getModularType())
                .tdp(entity.getTdp())
                .build();
    }

    public static PowerSupplyDTO initDTOFromRequest(final PowerSupplyCreationRequest request) {
        return PowerSupplyDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .efficiencyRating(EfficiencyRating.valueOf(request.getEfficiencyRating().trim().toUpperCase()))
                .wattage(Integer.valueOf(request.getWattage()))
                .modularType(ModularType.valueOf(request.getModularType().trim().toUpperCase()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}