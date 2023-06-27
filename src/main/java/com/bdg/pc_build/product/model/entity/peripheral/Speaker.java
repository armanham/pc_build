package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.util.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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
    private Long id;

    @Column(name = "frequency", nullable = false, updatable = false)
    private Integer frequency;

    @Column(name = "power_source", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private PowerSourceType powerSourceType;

    @Column(name = "cable_length", nullable = false, updatable = false)
    private Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    private String dimension;

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