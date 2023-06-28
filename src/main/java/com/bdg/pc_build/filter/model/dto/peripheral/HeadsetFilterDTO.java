package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.HeadsetFilterRequest;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class HeadsetFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minFrequency;
    private final Integer maxFrequency;

    private final Double minCableLength;
    private final Double maxCableLength;

    private final Set<ConnectivityType> connectivityTypes;

    public HeadsetFilterDTO(final HeadsetFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }

        this.minFrequency = request.minFrequency();
        this.maxFrequency = request.maxFrequency();
        if (this.minFrequency != null && this.maxFrequency != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minFrequency, maxFrequency);
        }

        this.minCableLength = request.minCableLength();
        this.maxCableLength = request.maxCableLength();
        if (this.minCableLength != null && this.maxCableLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCableLength, maxCableLength);
        }

        if (request.connectivityTypes() != null && !request.connectivityTypes().isEmpty()) {
            this.connectivityTypes = request.connectivityTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> ConnectivityType.toListOfStrings().contains(s))
                    .map(ConnectivityType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.connectivityTypes = null;
        }
    }
}