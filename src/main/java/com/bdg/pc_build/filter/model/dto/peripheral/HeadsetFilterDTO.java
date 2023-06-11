package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.HeadsetFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class HeadsetFilterDTO {

    Double minPrice;
    Double maxPrice;

    Integer minFrequency;
    Integer maxFrequency;

    String connectivity;

    Double minCableLength;
    Double maxCableLength;

    public HeadsetFilterDTO(final HeadsetFilterRequest request) {
        this.connectivity = request.connectivity();
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
//        if (request.frequency() != null && !request.frequency().isBlank()) {
//            this.frequency = Integer.valueOf(request.frequency());
//        }
//        if (request.cableLength() != null && !request.cableLength().isBlank()) {
//            this.cableLength = Double.valueOf(request.cableLength());
//        }
    }
}