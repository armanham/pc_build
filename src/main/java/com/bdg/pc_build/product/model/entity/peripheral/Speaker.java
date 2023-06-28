package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.product.enumerations.PowerSourceType;
import com.bdg.pc_build.product.model.dto.peripheral.SpeakerDTO;
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
@Table(name = "speaker", schema = "product")
public class Speaker extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "speaker_seq")
    @SequenceGenerator(name = "speaker_seq", sequenceName = "speaker_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_SPEAKER, allocationSize = 1
    )
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "speakers")
    List<Order> orders;

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