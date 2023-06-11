package com.bdg.pc_build.filter.model.dto.main_component;

import com.bdg.pc_build.filter.model.request.main_component.CPUCoolerFilterRequest;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CPUCoolerFilterDTO {

    Double minPrice;
    Double maxPrice;

    Integer minFanRpm;
    Integer maxFanRpm;

    Integer minTdp;
    Integer maxTdp;

    SocketType socketType;

    public CPUCoolerFilterDTO(final CPUCoolerFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minFanRpm() != null && !request.minFanRpm().isBlank()) {
            this.minFanRpm = Integer.valueOf(request.maxFanRpm());
        }
        if (request.maxFanRpm() != null && !request.maxFanRpm().isBlank()) {
            this.maxFanRpm = Integer.valueOf(request.maxFanRpm());
        }
        if (request.minTdp() != null && !request.minTdp().isBlank()) {
            this.minTdp = Integer.valueOf(request.minTdp());
        }
        if (request.maxTdp() != null && !request.maxTdp().isBlank()) {
            this.maxTdp = Integer.valueOf(request.maxTdp());
        }
        //TODO SOCKETTYPE
    }
}