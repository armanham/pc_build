package com.bdg.pc_build.filter.model.dto.display;

import com.bdg.pc_build.filter.model.request.display.MonitorFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class MonitorFilterDTO {

    Double minPrice;
    Double maxPrice;
    Double screenSize;
    Integer refreshRate;
    String screenType;

    public MonitorFilterDTO(final MonitorFilterRequest request) {
        this.screenType = request.screenType();
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.screenSize() != null && !request.screenSize().isBlank()) {
            this.screenSize = Double.valueOf(request.screenSize());
        }
        if (request.refreshRate() != null && !request.refreshRate().isBlank()) {
            this.refreshRate = Integer.valueOf(request.refreshRate());
        }
    }
}