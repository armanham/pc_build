package com.bdg.pc_build.filter.model.dto.main;

import com.bdg.pc_build.filter.model.request.main.RAMFilterRequest;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class RAMFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minCountOfRam;
    private final Integer maxCountOfRam;

    private final Integer minGbOfRam;
    private final Integer maxGbOfRam;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<DDRType> ddrTypes;

    public RAMFilterDTO(final RAMFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minCountOfRam = request.minCountOfRam();
        this.maxCountOfRam = request.maxCountOfRam();
        if (this.minCountOfRam != null && this.maxCountOfRam != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCountOfRam, maxCountOfRam);
        }

        this.minGbOfRam = request.minGbOfRam();
        this.maxGbOfRam = request.maxGbOfRam();
        if (this.minGbOfRam != null && this.maxGbOfRam != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minGbOfRam, maxGbOfRam);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
        if (this.minTdp != null && this.maxTdp != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minTdp, maxTdp);
        }

        if (request.ddrTypes() != null && !request.ddrTypes().isEmpty()) {
            this.ddrTypes = request.ddrTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> DDRType.toSetOfStrings().contains(s))
                    .map(DDRType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.ddrTypes = null;
        }
    }
}