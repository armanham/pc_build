package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.PowerSupplyFilterRequest;
import com.bdg.pc_build.product.model.enumerations.EfficiencyRating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class PowerSupplyFilterDTO {

    Double minPrice;
    Double maxPrice;

    EfficiencyRating efficiencyRating;

    Integer minWattage;
    Integer maxWattage;

    Boolean modular;

    Integer minTdp;
    Integer maxTdp;

    public PowerSupplyFilterDTO(final PowerSupplyFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.efficiencyRating() != null && !request.efficiencyRating().isBlank()) {
            this.efficiencyRating = EfficiencyRating.valueOf(request.efficiencyRating());
        }
//        if (request.wattage() != null && !request.wattage().isBlank()) {
//            this.wattage = Integer.valueOf(request.wattage());
//        }
        if (request.modular() != null && !request.modular().isBlank()) {
            this.modular = Boolean.valueOf(request.modular());
        }
//        if (request.tdp() != null && !request.tdp().isBlank()) {
//            this.tdp = Integer.valueOf(request.tdp());
//        }
    }
}