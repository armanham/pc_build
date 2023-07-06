package com.bdg.pc_build.filter.model.dto.main;

import com.bdg.pc_build.filter.model.request.main.PowerSupplyFilterRequest;
import com.bdg.pc_build.product.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.enumerations.ModularType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class PowerSupplyFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minWattage;
    private final Integer maxWattage;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<ModularType> modularTypes;
    private final Set<EfficiencyRating> efficiencyRatings;

    public PowerSupplyFilterDTO(final PowerSupplyFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minWattage = request.minWattage();
        this.maxWattage = request.maxWattage();
        if (this.minWattage != null && this.maxWattage != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minWattage, maxWattage);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.modularTypes() != null && !request.modularTypes().isEmpty()) {
            this.modularTypes = request.modularTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> ModularType.toSetOfStrings().contains(s))
                    .map(ModularType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.modularTypes = null;
        }

        if (request.efficiencyRatings() != null && !request.efficiencyRatings().isEmpty()) {
            this.efficiencyRatings = request.efficiencyRatings()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> EfficiencyRating.toSetOfStrings().contains(s))
                    .map(EfficiencyRating::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.efficiencyRatings = null;
        }
    }
}