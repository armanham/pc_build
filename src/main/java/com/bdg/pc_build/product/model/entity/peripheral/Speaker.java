package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import javax.persistence.*;

import com.bdg.pc_build.product.model.enumerations.PowerSourceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "speaker")
public class Speaker extends Product {

    @Column(name = "frequency", nullable = false, updatable = false)
    Integer frequency;

    @Column(name = "power_source", nullable = false, updatable = false)
    PowerSourceType powerSourceType;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    String dimension;

    public Speaker(final SpeakerDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.frequency = dto.getFrequency();
        this.powerSourceType = dto.getPowerSourceType();
        this.cableLength = dto.getCableLength();
        this.dimension = dto.getDimension();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return Objects.equals(frequency, speaker.frequency)
                && Objects.equals(powerSourceType, speaker.powerSourceType)
                && Objects.equals(cableLength, speaker.cableLength)
                && Objects.equals(dimension, speaker.dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frequency, powerSourceType, cableLength, dimension);
    }
}
