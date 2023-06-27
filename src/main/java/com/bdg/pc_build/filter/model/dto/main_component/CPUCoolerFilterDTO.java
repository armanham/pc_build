package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CPUCoolerFilterRequest;
import com.bdg.pc_build.util.ValidationUtil;
import com.bdg.pc_build.product.enumerations.SocketType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CPUCoolerFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minFanRpm;
    private final Integer maxFanRpm;

    private final Integer minTdp;
    private final Integer maxTdp;

    private final Set<SocketType> socketTypes;

    public CPUCoolerFilterDTO(final CPUCoolerFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minFanRpm = request.minFanRpm();
        this.maxFanRpm = request.maxFanRpm();
        if (this.minFanRpm != null && this.maxFanRpm != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minFanRpm, maxFanRpm);
        }

        this.minTdp = request.minTdp();
        this.maxTdp = request.maxTdp();
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