package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.validaton.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.SpeakerFilterRequest;
import com.bdg.pc_build.product.enumerations.PowerSourceType;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SpeakerFilterDTO {

    private final String name;

    private Double minPrice;
    private Double maxPrice;

    private Integer minFrequency;
    private Integer maxFrequency;

    private Double minCableLength;
    private Double maxCableLength;

    private final Set<String> dimensions;

    private final Set<PowerSourceType> powerSourceTypes;

    public SpeakerFilterDTO(final SpeakerFilterRequest request) {
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

        if (request.dimensions() != null && !request.dimensions().isEmpty()) {
            this.dimensions = request.dimensions()
                    .stream()
                    .map(s -> s.toLowerCase().trim())
                    .collect(Collectors.toSet());
        } else {
            this.dimensions = null;
        }

        if (request.powerSourceTypes() != null && !request.powerSourceTypes().isEmpty()) {
            this.powerSourceTypes = request.powerSourceTypes()
                    .stream()
                    .map(s -> s.toUpperCase().trim())
                    .filter(s -> PowerSourceType.toListOfStrings().contains(s))
                    .map(PowerSourceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.powerSourceTypes = null;
        }
    }
}