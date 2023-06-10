package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.peripheral.HeadsetDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "headset")
public class Headset extends Product {

    @Column(name = "frequency", nullable = false, updatable = false)
    Integer frequency;

    @Column(name = "connectivity", nullable = false, updatable = false)
    String connectivity;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    public Headset(final HeadsetDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.frequency = dto.getFrequency();
        this.connectivity = dto.getConnectivity();
        this.cableLength = dto.getCableLength();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Headset headset = (Headset) o;
        return Objects.equals(frequency, headset.frequency)
                && Objects.equals(connectivity, headset.connectivity)
                && Objects.equals(cableLength, headset.cableLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, connectivity, cableLength);
    }
}
