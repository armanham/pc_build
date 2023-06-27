package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
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
@Table(name = "speaker", schema = "product")
public class Speaker extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "speaker_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_SPEAKER)
    @Column(name = "id")
    Long id;

    @Column(name = "frequency", nullable = false, updatable = false)
    Integer frequency;

    @Column(name = "power_source", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
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