package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.PowerSupplyFilterRequest;
import com.bdg.pc_build.product.model.enumerations.EfficiencyRating;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class PowerSupplyFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minWattage;
    Integer maxWattage;

    Integer minTdp;
    Integer maxTdp;

    final Set<Boolean> isModular;

    final Set<EfficiencyRating> efficiencyRatings;

    public PowerSupplyFilterDTO(final PowerSupplyFilterRequest request) {
        this.name = request.name();

        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        if (request.minWattage() != null && !request.minWattage().isBlank()) {
            this.minWattage = Integer.valueOf(request.minWattage());
        }
        if (request.maxWattage() != null && !request.maxWattage().isBlank()) {
            this.maxWattage = Integer.valueOf(request.maxWattage());
        }
        if (this.minWattage != null && this.maxWattage != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minWattage, maxWattage);
        }

        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.isModular() != null && !request.isModular().isEmpty()) {
            this.isModular = request.isModular()
                    .stream()
                    .map(String::trim)
                    .filter(s -> s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false"))
                    .map(Boolean::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.isModular = null;
        }

        if (request.efficiencyRatings() != null && !request.efficiencyRatings().isEmpty()) {
            this.efficiencyRatings = request.efficiencyRatings()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> EfficiencyRating.toListOfStrings().contains(s))
                    .map(EfficiencyRating::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.efficiencyRatings = null;
        }
    }
}