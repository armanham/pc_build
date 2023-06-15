package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.RAMFilterRequest;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RAMFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minCountOfRam;
    Integer maxCountOfRam;

    Double minGbOfRam;
    Double maxGbOfRam;

    Integer minTdp;
    Integer maxTdp;

    final Set<DDRType> ddrTypes;

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