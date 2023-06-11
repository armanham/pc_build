package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.RAMFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class RAMFilterDTO {

    Double minPrice;
    Double maxPrice;

    Integer minSpeed;
    Integer maxSpeed;

    Integer minCountOfRam;
    Integer maxCountOfRam;

    Double minGbOfRam;
    Double maxGbOfRam;

    Integer minTdp;
    Integer maxTdp;

    public RAMFilterDTO(final RAMFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
//        if (request.speed() != null && !request.speed().isBlank()) {
//            this.speed = Integer.valueOf(request.speed());
//        }
//        if (request.gbOfRam() != null && !request.gbOfRam().isBlank()) {
//            this.GBOfRAM = Double.valueOf(request.gbOfRam());
//        }
//        if (request.countOfRAM() != null && !request.countOfRAM().isBlank()) {
//            this.countOfRAM = Integer.valueOf(request.countOfRAM());
//        }
//        if (request.tdp() != null && !request.tdp().isBlank()) {
//            this.tdp = Integer.valueOf(request.tdp());
//        }
    }
}
