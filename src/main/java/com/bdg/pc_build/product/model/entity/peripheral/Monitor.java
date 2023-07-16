package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.MonitorScreenType;
import com.bdg.pc_build.product.model.dto.peripheral.MonitorDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.util.InitialAndFinalIdValues;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "monitor", schema = "product")
public class Monitor extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monitor_seq")
    @SequenceGenerator(name = "monitor_seq", sequenceName = "monitor_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_MONITOR, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "screen_size", nullable = false, updatable = false)
    private Double screenSize;

    @Column(name = "refresh_rate", nullable = false, updatable = false)
    private Integer refreshRate;

    @Enumerated(EnumType.STRING)
    @Column(name = "screen_type", nullable = false, updatable = false)
    private MonitorScreenType screenType;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "monitors")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "monitors")
    private List<Computer> computers;

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