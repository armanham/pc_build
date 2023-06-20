package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.HeadsetFilterRequest;
import com.bdg.pc_build.product.model.enumerations.ConnectivityType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class HeadsetFilterDTO {

    final String name;

    Double minPrice;
    Double maxPrice;

    Integer minFrequency;
    Integer maxFrequency;

    Double minCableLength;
    Double maxCableLength;

   final Set<ConnectivityType> connectivityTypes;

    public HeadsetFilterDTO(final HeadsetFilterRequest request) {
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

        if (request.minFrequency() != null && !request.minFrequency().isBlank()) {
            this.minFrequency = Integer.valueOf(request.minFrequency());
        }
        if (request.maxFrequency() != null && !request.maxFrequency().isBlank()) {
            this.maxFrequency = Integer.valueOf(request.maxFrequency());
        }
        if (this.minFrequency != null && this.maxFrequency != null) {
            ValidationUtil.validateNonNegativeMinMaxValues(minFrequency, maxFrequency);
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