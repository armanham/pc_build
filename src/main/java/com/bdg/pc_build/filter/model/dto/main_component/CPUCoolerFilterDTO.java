package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.main_component.CPUCoolerFilterRequest;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CPUCoolerFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minFanRpm;
    Integer maxFanRpm;

    Integer minTdp;
    Integer maxTdp;

    final Set<SocketType> socketTypes;

    public CPUCoolerFilterDTO(final CPUCoolerFilterRequest request) {
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

        if (request.minFanRpm() != null && !request.minFanRpm().isBlank()) {
            this.minFanRpm = Integer.valueOf(request.maxFanRpm());
        }
        if (request.maxFanRpm() != null && !request.maxFanRpm().isBlank()) {
            this.maxFanRpm = Integer.valueOf(request.maxFanRpm());
        }
        if (this.minFanRpm != null && this.maxFanRpm != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minFanRpm, maxFanRpm);
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

        if (request.socketTypes() != null && !request.socketTypes().isEmpty()) {
            this.socketTypes = request.socketTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> SocketType.toListOfStrings().contains(s))
                    .map(SocketType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.socketTypes = null;
        }
    }
}