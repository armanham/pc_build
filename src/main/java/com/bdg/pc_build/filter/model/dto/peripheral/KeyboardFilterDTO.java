package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.KeyboardFilterRequest;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class KeyboardFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Double minCableLength;
    private final Double maxCableLength;

    private final Double minWeight;
    private final Double maxWeight;

    private final Set<String> dimensions;

    private final Set<ConnectivityType> connectivityTypes;

    public KeyboardFilterDTO(final KeyboardFilterRequest request) {
        this.name = request.name();

        this.minPrice = request.minPrice();
        this.maxPrice = request.maxPrice();
        if (this.minPrice != null && this.maxPrice != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minPrice, maxPrice);
        }


        this.minCableLength = request.minCableLength();
        this.maxCableLength = request.maxCableLength();
        if (this.minCableLength != null && this.maxCableLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCableLength, maxCableLength);
        }

        this.minWeight = request.minWeight();
        this.maxWeight = request.maxWeight();
        if (this.minWeight != null && this.maxWeight != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minWeight, maxWeight);
        }

        if (request.dimensions() != null && !request.dimensions().isEmpty()) {
            this.dimensions = request.dimensions()
                    .stream()
                    .map(s -> s.toLowerCase().trim())
                    .collect(Collectors.toSet());
        } else {
            this.dimensions = null;
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