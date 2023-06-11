package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.MouseFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class MouseFilterDTO {

    Double minPrice;
    Double maxPrice;

    String type;

    Integer minResolution;
    Integer maxResolution;

    Double minCableLength;
    Double maxCableLength;

    Double minWeight;
    Double maxWeight;

    public MouseFilterDTO(final MouseFilterRequest request) {
        this.type = request.type();
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
//        if (request.maxResolution() != null && !request.maxResolution().isBlank()) {
//            this.maxResolution = Integer.valueOf(request.maxResolution());
//        }
//        if (request.cableLength() != null && !request.cableLength().isBlank()) {
//            this.cableLength = Double.valueOf(request.cableLength());
//        }
//        if (request.weight() != null && !request.weight().isBlank()) {
//            this.weight = Double.valueOf(request.weight());
//        }
    }
}