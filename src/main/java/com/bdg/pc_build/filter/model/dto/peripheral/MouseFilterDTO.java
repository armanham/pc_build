package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.checking.ValidationUtil;
import com.bdg.pc_build.filter.model.request.peripheral.MouseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

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

    final String type;

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

        if (request.minResolution() != null && !request.minResolution().isBlank()) {
            this.minResolution = Integer.valueOf(request.minResolution());
        }
        if (request.maxResolution() != null && !request.maxResolution().isBlank()) {
            this.maxResolution = Integer.valueOf(request.maxResolution());
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

        this.type = request.type();
    }
}