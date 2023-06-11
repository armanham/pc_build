package com.bdg.pc_build.filter.model.dto.peripheral;

import com.bdg.pc_build.filter.model.request.peripheral.ExternalHardDriveFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ExternalHardDriveFilterDTO {

    Double minPrice;
    Double maxPrice;
    Integer capacity;
    Integer tdp;

    public ExternalHardDriveFilterDTO(final ExternalHardDriveFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.capacity() != null && !request.capacity().isBlank()) {
            this.capacity = Integer.valueOf(request.capacity());
        }
        if (request.tdp() != null && !request.tdp().isBlank()) {
            this.tdp = Integer.valueOf(request.tdp());
        }
    }
}