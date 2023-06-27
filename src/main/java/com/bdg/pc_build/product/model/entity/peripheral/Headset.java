package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.util.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "headset", schema = "product")
public class Headset extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "headset_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_HEADSET)
    @Column(name = "id")
    private Long id;

    @Column(name = "frequency", nullable = false, updatable = false)
    private Integer frequency;

    @Column(name = "connectivity_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ConnectivityType connectivityType;

    @Column(name = "cable_length", nullable = false, updatable = false)
    private Double cableLength;

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