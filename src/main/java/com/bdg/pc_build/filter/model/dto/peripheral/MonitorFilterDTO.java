package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.MonitorFilterRequest;
import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class MonitorFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Double minScreenSize;
    private final Double maxScreenSize;

    private final Integer minRefreshRate;
    private final Integer maxRefreshRate;

    private final Set<MonitorScreenType> screenTypes;

    public MonitorFilterDTO(final MonitorFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minScreenSize = request.minScreenSize();
        this.maxScreenSize = request.maxScreenSize();
        if (minScreenSize != null && maxScreenSize != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minScreenSize, maxScreenSize);
        }

        this.minRefreshRate = request.minRefreshRate();
        this.maxRefreshRate = request.maxRefreshRate();
        if (minRefreshRate != null && maxRefreshRate != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minRefreshRate, maxRefreshRate);
        }

        if (request.screenTypes() != null && !request.screenTypes().isEmpty()) {
            this.screenTypes = request.screenTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> MonitorScreenType.toListOfStrings().contains(s))
                    .map(MonitorScreenType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.screenTypes = null;
        }
    }
}