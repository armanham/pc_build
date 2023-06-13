package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.PowerSupply;
import com.bdg.pc_build.product.model.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
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
@Setter
public class PowerSupplyDTO extends ProductDTO {

    EfficiencyRating efficiencyRating;
    Integer wattage;
    Boolean modular;
    Integer tdp;

    @Builder
    public PowerSupplyDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final EfficiencyRating efficiencyRating,
            final Integer wattage,
            final Boolean modular,
            final Integer tdp) {
        super(name, price, purchasedPrice, count);
        this.efficiencyRating = efficiencyRating;
        this.wattage = wattage;
        this.modular = modular;
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
                .modular(entity.getModular())
                .tdp(entity.getTdp())
                .build();
    }

    public static PowerSupplyDTO initDTOFromRequest(final ProductRequest request) {
        return PowerSupplyDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .efficiencyRating(EfficiencyRating.valueOf(request.efficiencyRating()))
                .wattage(Integer.valueOf(request.wattage()))
                .modular(Boolean.valueOf(request.modular()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}