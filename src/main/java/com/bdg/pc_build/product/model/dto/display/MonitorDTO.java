package com.bdg.pc_build.product.model.dto.display;

import com.bdg.pc_build.product.model.entity.display.Monitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MonitorDTO extends ProductDTO{

    Double screenSize;
    Integer refreshRate;
    String screenType;

    public MonitorDTO(String name, Double price, Integer count, Double screenSize, Integer refreshRate, String screenType) {
        super(name, price, count);
        this.screenSize = screenSize;
        this.refreshRate = refreshRate;
        this.screenType = screenType;
    }

    public static MonitorDTO initDTOFromEntity(final Monitor entity) {
        return new MonitorDTO(
                entity.getName(),
                entity.getPrice(),
                entity.getCount(),
                entity.getScreenSize(),
                entity.getRefreshRate(),
                entity.getScreenType()
        );
    }
}
