package com.bdg.pc_build.product.model.entity.display;

import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;


@Entity
@Table(name = "monitor")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Monitor extends Product {

    @Column(name = "screen_size", nullable = false, updatable = false)
    Double screenSize;

    @Column(name = "refresh_rate", nullable = false, updatable = false)
    Integer refreshRate;

    @Column(name = "screen_type", nullable = false, updatable = false)
    String screenType;

    public Monitor(final MonitorDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.screenSize = dto.getScreenSize();
        this.refreshRate = dto.getRefreshRate();
        this.screenType = dto.getScreenType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equals(screenSize, monitor.screenSize)
                && Objects.equals(refreshRate, monitor.refreshRate)
                && Objects.equals(screenType, monitor.screenType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screenSize, refreshRate, screenType);
    }
}
