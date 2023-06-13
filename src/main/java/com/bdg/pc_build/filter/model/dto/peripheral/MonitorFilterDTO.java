package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.MonitorFilterRequest;
import com.bdg.pc_build.product.model.enumerations.MonitorScreenType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MonitorFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Double minScreenSize;
    Double maxScreenSize;

    Integer minRefreshRate;
    Integer maxRefreshRate;

    List<MonitorScreenType> screenTypes;

    public MonitorFilterDTO(final MonitorFilterRequest request) {
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

        if (request.minScreenSize() != null && !request.minScreenSize().isBlank()) {
            this.minScreenSize = Double.valueOf(request.minScreenSize());
        }
        if (request.maxScreenSize() != null && !request.maxScreenSize().isBlank()) {
            this.maxScreenSize = Double.valueOf(request.maxScreenSize());
        }
        if (minScreenSize != null && maxScreenSize != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minScreenSize, maxScreenSize);
        }

        if (request.minRefreshRate() != null && !request.minRefreshRate().isBlank()) {
            this.minRefreshRate = Integer.valueOf(request.minRefreshRate());
        }
        if (request.maxRefreshRate() != null && !request.maxRefreshRate().isBlank()) {
            this.maxRefreshRate = Integer.valueOf(request.maxRefreshRate());
        }
        if (minRefreshRate != null && maxRefreshRate != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minRefreshRate, maxRefreshRate);
        }

        if (request.screenTypes() != null && !request.screenTypes().isEmpty()) {
            this.screenTypes = request.screenTypes()
                    .stream()
                    .filter(s -> MonitorScreenType.toListOfStrings().contains(s))
                    .map(MonitorScreenType::valueOf)
                    .collect(Collectors.toList());
            System.out.println(screenTypes);
        } else {
            this.screenTypes = null;
        }
    }
}