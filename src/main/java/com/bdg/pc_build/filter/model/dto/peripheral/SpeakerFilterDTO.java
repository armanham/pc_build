package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.SpeakerFilterRequest;
import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.util.ValidationUtil;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SpeakerFilterDTO {

    private final String name;

    private final Double minPrice;
    private final Double maxPrice;

    private final Integer minFrequency;
    private final Integer maxFrequency;

    private final Double minCableLength;
    private final Double maxCableLength;

    private final Set<String> dimensions;

    private final Set<PowerSourceType> powerSourceTypes;

    public SpeakerFilterDTO(final SpeakerFilterRequest request) {
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
                    .filter(s -> PowerSourceType.toSetOfStrings().contains(s))
                    .map(PowerSourceType::valueOf)
                    .collect(Collectors.toSet());
        } else {
            this.powerSourceTypes = null;
        }
    }
}