package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
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
@Table(name = "headset", schema = "product")
public class Headset extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "headset_seq")
    @SequenceGenerator(name = "headset_seq", sequenceName = "headset_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_HEADSET, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "frequency", nullable = false, updatable = false)
    private Integer frequency;

    @Column(name = "connectivity_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ConnectivityType connectivityType;

    @Column(name = "cable_length", nullable = false, updatable = false)
    private Double cableLength;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "headsets")
    List<Order> orders;

    public Headset(final HeadsetDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.frequency = dto.getFrequency();
        this.connectivityType = dto.getConnectivityType();
        this.cableLength = dto.getCableLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Headset headset = (Headset) o;
        return Objects.equals(frequency, headset.frequency)
                && Objects.equals(connectivityType, headset.connectivityType)
                && Objects.equals(cableLength, headset.cableLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, connectivityType, cableLength);
    }
}