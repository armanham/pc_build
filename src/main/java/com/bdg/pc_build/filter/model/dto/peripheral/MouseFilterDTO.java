package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.MouseFilterRequest;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class MouseFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minResolution;
    Integer maxResolution;

    Double minCableLength;
    Double maxCableLength;

    Double minWeight;
    Double maxWeight;

    final Set<ConnectivityType> connectivityTypes;

    public MouseFilterDTO(final MouseFilterRequest request) {
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

        if (request.minMaxResolution() != null && !request.minMaxResolution().isBlank()) {
            this.minResolution = Integer.valueOf(request.minMaxResolution());
        }
        if (request.maxMaxResolution() != null && !request.maxMaxResolution().isBlank()) {
            this.maxResolution = Integer.valueOf(request.maxMaxResolution());
        }
        if (this.minResolution != null && this.maxResolution != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minResolution, maxResolution);
        }

        if (request.minCableLength() != null && !request.minCableLength().isBlank()) {
            this.minCableLength = Double.valueOf(request.minCableLength());
        }
        if (request.maxCableLength() != null && !request.maxCableLength().isBlank()) {
            this.maxCableLength = Double.valueOf(request.maxCableLength());
        }
        if (this.minCableLength != null && this.maxCableLength != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minCableLength, maxCableLength);
        }

        if (request.minWeight() != null && !request.minWeight().isBlank()) {
            this.minWeight = Double.valueOf(request.minWeight());
        }
        if (request.maxWeight() != null && !request.maxWeight().isBlank()) {
            this.maxWeight = Double.valueOf(request.maxWeight());
        }
        if (this.minWeight != null && this.maxWeight != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minWeight, maxWeight);
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

//        this.connectivityTypes = request.connectivityTypes();
    }
}