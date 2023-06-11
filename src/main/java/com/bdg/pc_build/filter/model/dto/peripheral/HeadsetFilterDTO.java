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

    Double minCableLength;
    Double maxCableLength;

    String connectivity;


    public HeadsetFilterDTO(final HeadsetFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minFrequency() != null && !request.minFrequency().isBlank()) {
            this.minFrequency = Integer.valueOf(request.minFrequency());
        }
        if (request.maxFrequency() != null && !request.maxFrequency().isBlank()) {
            this.maxFrequency = Integer.valueOf(request.maxFrequency());
        }
        if (request.minCableLength() != null && !request.minCableLength().isBlank()) {
            this.minCableLength = Double.valueOf(request.minCableLength());
        }
        if (request.maxCableLength() != null && !request.maxCableLength().isBlank()) {
            this.maxCableLength = Double.valueOf(request.maxCableLength());
        }
        this.connectivity = request.connectivity();
    }
}