package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.KeyboardFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class KeyboardFilterDTO {

    Double minPrice;
    Double maxPrice;

    Double minCableLength;
    Double maxCableLength;

    Double minWeight;
    Double maxWeight;

    String keyboardClass;

    String dimension;

    public KeyboardFilterDTO(final KeyboardFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minCableLength() != null && !request.minCableLength().isBlank()) {
            this.minCableLength = Double.valueOf(request.minCableLength());
        }
        if (request.maxCableLength() != null && !request.maxCableLength().isBlank()) {
            this.maxCableLength = Double.valueOf(request.maxCableLength());
        }
        if (request.minWeight() != null && !request.minWeight().isBlank()) {
            this.minWeight = Double.valueOf(request.minWeight());
        }
        if (request.maxWeight() != null && !request.maxWeight().isBlank()) {
            this.maxWeight = Double.valueOf(request.maxWeight());
        }
        this.keyboardClass = request.keyboardClass();
        this.dimension = request.dimension();
    }
}