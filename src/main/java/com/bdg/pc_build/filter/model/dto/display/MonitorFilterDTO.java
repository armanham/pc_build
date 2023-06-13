package com.bdg.pc_build.filter.model.dto.display;

import com.bdg.pc_build.filter.model.request.display.MonitorFilterRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class MonitorFilterDTO {

    String name;

    Double minPrice;
    Double maxPrice;

    Double minScreenSize;
    Double maxScreenSize;

    Integer minRefreshRate;
    Integer maxRefreshRate;

    String screenTypes;

    public MonitorFilterDTO(final MonitorFilterRequest request) {
        this.name = request.name();

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
        if (request.screenTypes() != null && !request.screenTypes().isEmpty()) {
            this.screenTypes =
                    request.screenTypes()
                    .stream()
                    .map(s -> "'" + s + "'")
                    .collect(Collectors.joining(","))
                    .toLowerCase()
            ;
            System.out.println(screenTypes);
        } else {
            this.screenTypes = null;
        }
    }
}