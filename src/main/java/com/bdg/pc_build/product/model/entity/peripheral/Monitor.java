package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "monitor", schema = "product")
public class Monitor extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "monitor_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_MONITOR)
    @Column(name = "id")
    Long id;

    @Column(name = "screen_size", nullable = false, updatable = false)
    Double screenSize;

    @Column(name = "refresh_rate", nullable = false, updatable = false)
    Integer refreshRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "screen_type", nullable = false, updatable = false)
    MonitorScreenType screenType;

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