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

    Double minScreenSize;
    Double maxScreenSize;

    Integer minRefreshRate;
    Integer maxRefreshRate;

    String screenType;

    public MonitorFilterDTO(final MonitorFilterRequest request) {
        if (request.minPrice() != null && !request.minPrice().isBlank()) {
            this.minPrice = Double.valueOf(request.minPrice());
        }
        if (request.maxPrice() != null && !request.maxPrice().isBlank()) {
            this.maxPrice = Double.valueOf(request.maxPrice());
        }
        if (request.minScreenSize() != null && !request.minScreenSize().isBlank()) {
            this.minScreenSize = Double.valueOf(request.minScreenSize());
        }
        if (request.maxScreenSize() != null && !request.maxScreenSize().isBlank()) {
            this.maxScreenSize = Double.valueOf(request.maxScreenSize());
        }
        if (request.minRefreshRate() != null && !request.minRefreshRate().isBlank()) {
            this.minRefreshRate = Integer.valueOf(request.minRefreshRate());
        }
        if (request.maxRefreshRate() != null && !request.maxRefreshRate().isBlank()) {
            this.maxRefreshRate = Integer.valueOf(request.maxRefreshRate());
        }
        this.screenType = request.screenType();
    }
}