package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
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
@Table(name = "mouse", schema = "product")
public class Mouse extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mouse_seq")
    @SequenceGenerator(
            name = "mouse_seq", sequenceName = "mouse_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_MOUSE, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "connectivity_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ConnectivityType connectivityType;

    @Column(name = "max_resolution", nullable = false, updatable = false)
    private Integer maxResolution;

    @Column(name = "cable_length", nullable = false, updatable = false)
    private Double cableLength;

    @Column(name = "weight", nullable = false, updatable = false)
    private Double weight;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "mice")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "mice")
    private List<Computer> computers;

    public Mouse(final MouseDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.connectivityType = dto.getConnectivityType();
        this.maxResolution = dto.getMaxResolution();
        this.cableLength = dto.getCableLength();
        this.weight = dto.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return Objects.equals(connectivityType, mouse.connectivityType)
                && Objects.equals(maxResolution, mouse.maxResolution)
                && Objects.equals(cableLength, mouse.cableLength)
                && Objects.equals(weight, mouse.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectivityType, maxResolution, cableLength, weight);
    }
}