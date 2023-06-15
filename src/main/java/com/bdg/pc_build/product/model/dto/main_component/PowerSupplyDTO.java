package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.model.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.model.request.creation.main_component.PowerSupplyCreationRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Arman Hakhverdyan
 * <p>
 * DTO (Data Transfer Object) representing a power supply.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class PowerSupplyDTO extends ProductDTO {

    EfficiencyRating efficiencyRating;
    Integer wattage;
    Boolean isModular;
    Integer tdp;

    @Builder
    public PowerSupplyDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final EfficiencyRating efficiencyRating,
            final Integer wattage,
            final Boolean isModular,
            final Integer tdp)
    {
        super(name, price, purchasedPrice, count);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.isModular = isModular;
        this.tdp = tdp;
    }

    public static PowerSupplyDTO initDTOFromEntity(final PowerSupply entity) {
        return PowerSupplyDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .efficiencyRating(entity.getEfficiencyRating())
                .wattage(entity.getWattage())
                .isModular(entity.getIsModular())
                .tdp(entity.getTdp())
                .build();
    }

    public static PowerSupplyDTO initDTOFromRequest(final PowerSupplyCreationRequest request) {
        return PowerSupplyDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .efficiencyRating(EfficiencyRating.valueOf(request.getEfficiencyRating()))
                .wattage(Integer.valueOf(request.getWattage()))
                .isModular(Boolean.valueOf(request.getIsModular()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}