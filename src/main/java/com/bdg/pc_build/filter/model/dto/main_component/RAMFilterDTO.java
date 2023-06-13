package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.RAMFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class RAMFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minSpeed;
    Integer maxSpeed;

    Integer minCountOfRam;
    Integer maxCountOfRam;

    Double minGbOfRam;
    Double maxGbOfRam;

    Integer minTdp;
    Integer maxTdp;

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

        if (request.minSpeed() != null && !request.minSpeed().isBlank()) {
            this.minSpeed = Integer.valueOf(request.minSpeed());
        }
        if (request.maxSpeed() != null && !request.maxSpeed().isBlank()) {
            this.maxSpeed = Integer.valueOf(request.maxSpeed());
        }
        if (this.minSpeed != null && this.maxSpeed != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minSpeed, maxSpeed);
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
    }
}