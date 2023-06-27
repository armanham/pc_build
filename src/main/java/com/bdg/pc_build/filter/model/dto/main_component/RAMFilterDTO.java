package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.RAMFilterRequest;
import com.bdg.pc_build.product.enumerations.DDRType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class RAMFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    private Integer minCountOfRam;
    private Integer maxCountOfRam;

    private Double minGbOfRam;
    private Double maxGbOfRam;

    private Integer minTdp;
    private Integer maxTdp;

    private final Set<DDRType> ddrTypes;

    public RAMFilterDTO(final RAMFilterRequest request) {
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

        if (request.minCountOfRam() != null && !request.minCountOfRam().isBlank()) {
            this.minCountOfRam = Integer.valueOf(request.minCountOfRam());
        }
        if (request.maxCountOfRam() != null && !request.maxCountOfRam().isBlank()) {
            this.maxCountOfRam = Integer.valueOf(request.maxCountOfRam());
        }
        if (this.minCountOfRam != null && this.maxCountOfRam != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCountOfRam, maxCountOfRam);
        }

        if (request.minGbOfRam() != null && !request.minGbOfRam().isBlank()) {
            this.minGbOfRam = Double.valueOf(request.minGbOfRam());
        }
        if (request.maxGbOfRam() != null && !request.maxGbOfRam().isBlank()) {
            this.maxGbOfRam = Double.valueOf(request.maxGbOfRam());
        }
        if (this.minGbOfRam != null && this.maxGbOfRam != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minGbOfRam, maxGbOfRam);
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

        if (request.ddrTypes() != null && !request.ddrTypes().isEmpty()) {
            this.ddrTypes = request.ddrTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> DDRType.toListOfStrings().contains(s))
                    .map(DDRType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.ddrTypes = null;
        }
    }
}