package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.FilterRequestForKeyboard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class FilterDTOForKeyboard {

    Double minPrice;
    Double maxPrice;
    String keyboardClass;
    Double cableLength;
    String dimension;
    Double weight;

    public FilterDTOForKeyboard(final FilterRequestForKeyboard request) {
        this.keyboardClass = request.keyboardClass();
        this.dimension = request.dimension();
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.cableLength() != null && !request.cableLength().isBlank()) {
            this.cableLength = Double.valueOf(request.cableLength());
        }
        if (request.weight() != null && !request.weight().isBlank()) {
            this.weight = Double.valueOf(request.weight());
        }
    }
}